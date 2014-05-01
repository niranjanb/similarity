package org.allenai.similarity.client

import spray.routing.HttpService

trait AssetRoutes { self: HttpService =>

  object assetRoutes {

    private def public(rest: String) = s"web/public/main/$rest"

    private def webModule(rest: String) = s"web/web-modules/main/webjars/lib/$rest"

    val indexRoute = get {
      //getFromFile(s"target/web/public/main/index.html")
      getFromResource(public("index.html"))
    }

    val assetsRoute = get {
      path(Rest) { rest =>
        getFromResource(public(rest))
      }
    }

    val webjarsRoute = get {
      path(Rest) { rest =>
        getFromResource(webModule(rest))
      }
    }
  }
}
