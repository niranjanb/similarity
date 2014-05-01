package org.allenai.similarity.routes

import org.allenai.similarity.SimilarityServiceActor
import org.allenai.similarity.models.SimilarityRequest
import spray.json.DefaultJsonProtocol._

trait ApiRoute { self: SimilarityServiceActor =>

  object apiRoute {

    val route =

    // format: OFF
    path("string-compare") {
      post {
        entity(as[SimilarityRequest]) { similarityRequest =>
          // get an execution context:
          import context.dispatcher
          onSuccess(appContext.similarity(similarityRequest)) {result =>
            log.info("Request: " + similarityRequest)
            log.info("Result: " + result)
            complete(result.toSeq)
          }
        }
      }
    }
    // format: ON
  }

}
