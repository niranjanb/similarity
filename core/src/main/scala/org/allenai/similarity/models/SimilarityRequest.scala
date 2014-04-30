package org.allenai.similarity.models

import spray.json.DefaultJsonProtocol._

case class SimilarityRequest(text: String, hypothesis: String)

object SimilarityRequest{
  implicit val jsonFormat = jsonFormat2(SimilarityRequest.apply)
}
