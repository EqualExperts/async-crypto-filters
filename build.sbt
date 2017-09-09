import uk.gov.hmrc.DefaultBuildSettings.targetJvm

enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)

name := "microservice-async"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8")
targetJvm := "jvm-1.8"

libraryDependencies ++= AppDependencies()

headers := EEHeaderSettings()
organizationHomepage := Some(url("https://www.equalexperts.com"))
organization := "com.equalexperts"

resolvers := Seq(
  Resolver.bintrayRepo("equalexperts", "open-source-release-candidates"),
  Resolver.bintrayRepo("hmrc", "releases"),
  Resolver.typesafeRepo("releases")
)