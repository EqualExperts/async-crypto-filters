
# microservice-async

[![Build Status](https://travis-ci.org/hmrc/microservice-async.svg?branch=master)](https://travis-ci.org/hmrc/microservice-async) [ ![Download](https://api.bintray.com/packages/hmrc/releases/microservice-async/images/download.svg) ](https://bintray.com/hmrc/releases/microservice-async/_latestVersion)

Library extends the play-async library to provide extensions for both a mongo cache repository and the filter SessionCookieCryptoFilter is used to support state between service API calls.



## Installing

Include the following dependency in your SBT build

``` scala
resolvers += Resolver.bintrayRepo("hmrc", "releases")

libraryDependencies += "uk.gov.hmrc" %% "microservice-async" % "[INSERT-VERSION]"
```

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
