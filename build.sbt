name := "talk"

version := "0.1"

scalaVersion := "2.13.1"

//libraryDependencies += "org.bouncycastle" % "bcpkix-jdk15on" % "1.64"
libraryDependencies += "org.bouncycastle" % "bcprov-debug-jdk15on" % "1.64"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

scalacOptions := Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Ywarn-unused:locals,privates"
)
