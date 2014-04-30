package org.allenai.similarity

import unfiltered.request.{HttpRequest, Path, GET}
import unfiltered.response.{Ok, ResponseString}
import unfiltered.netty.ReceivedMessage
import org.allenai.similarity.word2vec.Word2VecSimilarity

import com.typesafe.config.ConfigFactory

sealed class MeasureType
object Cosine extends MeasureType

case class SimilarityRequest(text: String, hypothesis: String, measureType:Option[MeasureType])

case class SimilarityResponse(request:SimilarityRequest, confidence:Double)

object SimilarityRequestHelper {

  val Text = "text"
  val Hypothesis = "hypothesis"
  val MeasureType = "measureType"
  def param(request: HttpRequest[ReceivedMessage], name:String) = request.parameterValues(name).headOption

  def parse(request: HttpRequest[ReceivedMessage]) = {
    val measureType = param(request, MeasureType) match {
      case Some("cosine") => Some(Cosine)
      case _ => None
    }
    (param(request, Text), param(request, Hypothesis)) match {
      case (Some(t:String), Some(h:String)) => SimilarityRequest(t, h, measureType)
    }
  }
}

object SimilarityHttpServer extends App {

  def handle(query:SimilarityRequest) = SimilarityResponse(query, word2Vec.similarity(query.text, query.hypothesis))

  val similarity = unfiltered.netty.cycle.Planify {
    case req@GET(Path("/similarity")) =>
      val query:SimilarityRequest = SimilarityRequestHelper.parse(req)
      ResponseString(handle(query).toString()) ~> Ok
    case _ => ResponseString("Not a valid route. Try /similarity?text=cat&hypothesis=dog")
  }

  val config = ConfigFactory.load()
  val port = config.getInt("port")
  val vectorsFile = config.getString("word2vec.modelFile")
  val word2Vec = new Word2VecSimilarity(vectorsFile)
  unfiltered.netty.Http(port).plan(similarity).run()
}
