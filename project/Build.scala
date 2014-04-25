import sbt._
import Keys._

import com.typesafe.sbt.SbtNativePackager._
import sbtrelease.ReleasePlugin._
import spray.revolver.RevolverPlugin._

object SimilarityBuild extends Build {

  val allenAiCommon = "org.allenai.common" %% "common" % "0.0.1-SNAPSHOT"
  val ariInterface = "org.allenai.ari" %% "ari-interface" % "2013.4.9-1-SNAPSHOT"

  val akkaVersion = "2.3.2"
  def akkaModule(id: String) = "com.typesafe.akka" %% s"akka-$id" % akkaVersion
  val akkaActor = akkaModule("actor")
  val akkaLogging = akkaModule("slf4j")
  val akkaTestkit = akkaModule("testkit")

  // Spray
  val sprayVersion = "1.3.1"
  def sprayModule(id: String) = "io.spray" % s"spray-$id" % sprayVersion
  val sprayCan = sprayModule("can")
  val sprayRouting = sprayModule("routing")
  val sprayClient = sprayModule("client")
  val sprayTestkit = sprayModule("testkit")



}