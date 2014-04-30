package org.allenai.similarity.models

import spray.json._
import spray.json.DefaultJsonProtocol._

case class SimilarityResult(request:SimilarityRequest, method: String, confidence:Double)

object SimilarityResult{
  implicit val jsonFormat = jsonFormat3(SimilarityResult.apply)
}