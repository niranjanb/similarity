package org.allenai.similarity

import unfiltered.request.{HttpRequest, Path, GET}
import unfiltered.response.{Ok, ResponseString, Pass}
import unfiltered.netty.ReceivedMessage
import org.allenai.similarity.word2vec.Word2VecSimilarity


object RequestHelper {
  def parse(request: HttpRequest[ReceivedMessage]) = (request.parameterValues("text").head, request.parameterValues("hypothesis").head)
}
object SimilarityHttpServer extends App {
  val word2Vec = new Word2VecSimilarity("vectors.bin")
  val similarity = unfiltered.netty.cycle.Planify {
    case req@GET(Path("/similarity")) =>
      val (text:String, hypothesis:String) = RequestHelper.parse(req)
      ResponseString(f"similarity($text, $hypothesis) = ${word2Vec.similarity(text, hypothesis)}%02f") ~> Ok
    case _ => ResponseString("Not a valid route. Try /similarity?text=cat&hypothesis=dog")
  }
  unfiltered.netty.Http(8080).plan(similarity).run()
}
