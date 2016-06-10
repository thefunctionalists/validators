name := "validators"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.10.6", "2.11.8")

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.3" % "test",
  "org.specs2" %% "specs2-core" % "3.8.3" % "test"
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)

scalacOptions in Test ++= Seq("-Yrangepos")
