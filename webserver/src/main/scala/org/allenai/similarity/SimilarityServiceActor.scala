package org.allenai.similarity

import org.allenai.similarity.routes.Routes
import spray.json.DefaultJsonProtocol._

import akka.actor._
import scala.util.control.NonFatal
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.routing.ExceptionHandler
import spray.routing.HttpServiceActor

class SimilarityServiceActorImpl(val appContext: Context) extends SimilarityServiceActor with Routes {

  implicit def exceptionHandler = ExceptionHandler {
    case NonFatal(e) => requestUri { uri =>
      log.warning("Request to {} could not be handled normally", uri)
      complete(StatusCodes.InternalServerError -> e)
    }
  }

  override def receive = runRoute(route)
}

trait SimilarityServiceActor extends HttpServiceActor with ActorLogging with SprayJsonSupport {
  def appContext: Context
}

object SimilarityServiceActor {
  def props(appContext: Context): Props = Props(classOf[SimilarityServiceActorImpl], appContext)
}
