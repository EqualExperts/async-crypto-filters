FROM hseeberger/scala-sbt

WORKDIR /async-crypto-filters

COPY ./.git /async-crypto-filters/.git/

COPY ./build.sbt /async-crypto-filters

COPY ./bintray.sbt /async-crypto-filters

COPY ./LICENSE /async-crypto-filters

COPY ./project/*.sbt /async-crypto-filters/project/

COPY ./project/*.scala /async-crypto-filters/project/

COPY ./src /async-crypto-filters/src/

RUN sbt test

CMD ["sbt", "publishLocal"]
