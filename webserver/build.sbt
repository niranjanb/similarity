import Dependencies._
import spray.revolver.RevolverPlugin.Revolver

resolvers += "spray repo" at "http://repo.spray.io"

Revolver.settings

libraryDependencies ++= Seq(
  akkaModule("actor"),
  akkaModule("slf4j"),
  sprayModule("can"),
  sprayModule("routing"),
  "io.spray" %%  "spray-json" % "1.2.6",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe" % "config" % "1.2.0"
)
