
publishMavenStyle := !sbtPlugin.value
bintrayOrganization := Some("equalexperts")
bintrayRepository := {
  val candidateOrRelease = if(version.value.contains("-")) "open-source-release-candidates" else "open-source"
  if (sbtPlugin.value) s"sbt-plugin-$candidateOrRelease" else candidateOrRelease
}
bintrayPackage := name.value
licenses += ("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))