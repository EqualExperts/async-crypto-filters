# async-crypto-filters

[ ![Download](https://api.bintray.com/packages/equalexperts/open-source-release-candidates/async-crypto-filters/images/download.svg) ](https://bintray.com/equalexperts/open-source-release-candidates/async-crypto-filters/_latestVersion)
[![CircleCI](https://circleci.com/gh/EqualExperts/async-crypto-filters.svg?style=svg)](https://circleci.com/gh/EqualExperts/async-crypto-filters)

Library to provide a Play filter implementation for encryption and session cookies for use with [play-async](https://github.com/EqualExperts/play-async)


### Installing

Include the following dependency in your SBT build

* Release candidate versions

[ ![Download](https://api.bintray.com/packages/equalexperts/open-source-release-candidates/async-crypto-filters/images/download.svg) ](https://bintray.com/equalexperts/open-source-release-candidates/async-crypto-filters/_latestVersion)

```scala
resolvers += Resolver.bintrayRepo("equalexperts", "open-source-release-candidates")

libraryDependencies += "com.equalexperts" %% "async-crypto-filters" % "[INSERT-VERSION]"
```

* Released versions

TBC

```scala
resolvers += Resolver.bintrayRepo("equalexperts", "open-source")

libraryDependencies += "com.equalexperts" %% "async-crypto-filters" % "[INSERT-VERSION]"
```

### Building with Docker

`docker build -t async-crypto-filters:latest .`

### Publishing with Docker

`docker run -v ~/.ivy2:/root/.ivy2 -t async-crypto-filters:latest`


## Contributors 

This based off a forked from [/hmrc/microservice-async](https://github.com/hmrc/microservice-async)


### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
