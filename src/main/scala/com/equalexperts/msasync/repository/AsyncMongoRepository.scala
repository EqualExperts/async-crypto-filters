/*
 * Copyright 2017 Equal Experts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.equalexperts.msasync.repository

import play.api.libs.json._
import play.modules.reactivemongo.MongoDbConnection
import reactivemongo.api.{ReadPreference, DB}
import reactivemongo.api.indexes.{Index, IndexType}
import reactivemongo.bson._
import uk.gov.hmrc.mongo.json.ReactiveMongoFormats
import uk.gov.hmrc.mongo.{AtomicUpdate, BSONBuilderHelpers, DatabaseUpdate, ReactiveRepository}
import uk.gov.hmrc.play.asyncmvc.model.TaskCache
import uk.gov.hmrc.time.DateTimeUtils
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

case class TaskCachePersist(id: BSONObjectID, task:TaskCache)

object TaskCachePersist {

  val mongoFormats: Format[TaskCachePersist] = ReactiveMongoFormats.mongoEntity(
  {
    implicit val oidFormat = ReactiveMongoFormats.objectIdFormats
    Format(Json.reads[TaskCachePersist], Json.writes[TaskCachePersist])
  })
}

object AsyncRepository extends MongoDbConnection {
  lazy val mongo = new AsyncMongoRepository
  def apply(): AsyncRepository = mongo
}

class AsyncMongoRepository(implicit mongo: () => DB)
  extends ReactiveRepository[TaskCachePersist, BSONObjectID]("asynctaskcache", mongo, TaskCachePersist.mongoFormats, ReactiveMongoFormats.objectIdFormats)
          with AtomicUpdate[TaskCachePersist]
          with AsyncRepository
          with BSONBuilderHelpers {

  override def ensureIndexes(implicit ec: ExecutionContext): Future[scala.Seq[Boolean]] = {
    // set to zero for per document TTL using 'expiry' attribute to define the actual expiry time.
    val expireAfterSeconds = 0

    Future.sequence(
      Seq(
        collection.indexesManager.ensure(
          Index(Seq("task.id" -> IndexType.Ascending), name = Some("task.id"), unique = true)),
        collection.indexesManager.ensure(
          Index(
            key = Seq("expiry" -> IndexType.Ascending),
            options = BSONDocument("expireAfterSeconds" -> expireAfterSeconds)))
      )
    )
  }

  override def isInsertion(suppliedId: BSONObjectID, returned: TaskCachePersist): Boolean =
    suppliedId.equals(returned.id)

  private def modifierForInsert(task: TaskCache, expire:Long): BSONDocument = {
    val mandatory = BSONDocument(
      "$setOnInsert" -> BSONDocument("task.id" -> task.id),
      "$setOnInsert" -> BSONDocument("task.start" -> task.start),
      "$setOnInsert" -> BSONDocument("expiry" -> BSONDateTime(DateTimeUtils.now.getMillis + expire)),
      "$set" -> BSONDocument("task.complete" -> task.complete),
      "$set" -> BSONDocument("task.status" -> task.status)
    )

    val optional = task.jsonResponse.fold(BSONDocument.empty){ response => BSONDocument("$set" -> BSONDocument("task.jsonResponse" -> response)) }
    mandatory ++ optional
  }

  protected def findById(id: String) = BSONDocument("task.id" -> BSONString(id))

  override def findByTaskId(id: String): Future[Option[TaskCachePersist]] = {
    collection.find(findById(id)).one[TaskCachePersist](ReadPreference.primaryPreferred)
  }

  override def removeById(id: String): Future[Unit] = {
    import reactivemongo.bson.BSONDocument
    collection.remove(BSONDocument("task.id" -> id)).map(_ => {})
  }

  override def save(task: TaskCache, expire:Long): Future[DatabaseUpdate[TaskCachePersist]] = {
    atomicUpsert(findById(task.id), modifierForInsert(task, expire))
  }
}

trait AsyncRepository {
  def save(expectation: TaskCache, expire:Long): Future[DatabaseUpdate[TaskCachePersist]]
  def findByTaskId(id: String): Future[Option[TaskCachePersist]]
  def removeById(id: String): Future[Unit]
}
