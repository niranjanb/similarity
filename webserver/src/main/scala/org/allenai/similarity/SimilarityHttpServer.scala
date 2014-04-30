package org.allenai.similarity

import akka.actor._
import akka.io.IO
import spray.can.Http

object SimilarityHttpServer extends App with ProductionContext {

  implicit val system = ActorSystem("similarity-webapp")

  val service = system.actorOf(SimilarityServiceActor.props(this), "service")

  IO(Http) ! Http.Bind(service, "localhost", port = ConfigValues.port)
}
