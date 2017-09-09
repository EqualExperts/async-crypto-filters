FROM hseeberger/scala-sbt

WORKDIR /microservice-async

COPY ./.git /microservice-async/.git/

COPY ./build.sbt /microservice-async

COPY ./bintray.sbt /microservice-async

COPY ./LICENSE /microservice-async

COPY ./project/*.sbt /microservice-async/project/

COPY ./project/*.scala /microservice-async/project/

COPY ./src /microservice-async/src/

RUN sbt test

CMD ["sbt", "publishLocal"]
