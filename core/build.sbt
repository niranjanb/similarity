import Dependencies._

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  akkaModule("actor") % "provided",
  sprayModule("routing"),
  "io.spray" %%  "spray-json" % "1.2.6",
  "com.typesafe" % "config" % "1.2.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)
