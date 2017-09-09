# microservice-async

Library extends the play-async library to provide extensions for both a mongo cache repository and the filter SessionCookieCryptoFilter is used to support state between service API calls.

### Installing

Include the following dependency in your SBT build

``` scala
resolvers += Resolver.bintrayRepo("equalexperts", "open-source")

libraryDependencies += "com.equalexperts" %% "microservice-async" % "[INSERT-VERSION]"
```

### Building with Docker

`docker build -t emailaddress:latest .`

### Publishing with Docker

`docker run -v ~/.ivy2:/root/.ivy2 -t emailaddress:latest`


## Contributors 

This based off a forked from [/hmrc/microservice-async](https://github.com/hmrc/microservice-async)


### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
