package org.allenai.similarity.routes

import org.allenai.similarity.client.AssetRoutes
import org.allenai.similarity.SimilarityServiceActor

trait Routes
    extends AssetRoutes
    with ApiRoute { self: SimilarityServiceActor =>

  val route = {

    // format: OFF
    pathEndOrSingleSlash {
      assetRoutes.indexRoute
    } ~
    pathPrefix("assets") {
      pathPrefix("lib") {
        assetRoutes.webjarsRoute
      } ~
      assetRoutes.assetsRoute
    } ~
    pathPrefix("api") {
      apiRoute.route
    }
    // format: ON

  }
}
