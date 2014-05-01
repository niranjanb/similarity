import sbt._

object Dependencies {
  def sprayModule(id: String) = "io.spray" % s"spray-$id" % "1.3.1"
  def akkaModule(id: String) = "com.typesafe.akka" %% s"akka-$id" % "2.3.2"
}
