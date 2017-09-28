import sbt._

object AppDependencies {

  import play.core.PlayVersion

  val compile = Seq(
    "com.equalexperts" %% "play-async" % "0.1.0-9-gd6b4071" % "provided",
    "uk.gov.hmrc" %% "crypto" % "4.2.0",
    "uk.gov.hmrc" %% "play-filters" % "5.14.0"
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test: Seq[ModuleID] = ???
  }

  object Test {

    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "org.scalatest" %% "scalatest" % "3.0.3" % scope,
        "uk.gov.hmrc" %% "hmrctest" % "2.3.0" % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % scope,
        "org.mockito" % "mockito-all" % "1.9.5" % scope
      )
    }.test
  }
  def apply() = compile ++ Test()
}


