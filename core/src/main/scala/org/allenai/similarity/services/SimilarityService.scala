package org.allenai.similarity.services

import akka.actor.ActorSystem
import org.allenai.similarity.ConfigComponent

import spray.json._
import spray.json.DefaultJsonProtocol._
import spray.http._
import spray.httpx.SprayJsonSupport

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import java.net.URLEncoder
import org.allenai.similarity.models.{SimilarityResult, SimilarityRequest}
import org.allenai.similarity.implementations.{WordOverlap, Word2VecSimilarity}
import com.typesafe.config.ConfigFactory


trait SimilarityServiceComponent {

  protected def similarityServices: Iterable[SimilarityService]

  def similarity(request:SimilarityRequest)(implicit ec: ExecutionContext): Future[Iterable[SimilarityResult]] = {
    Future.sequence(similarityServices.map(service => service.similarity(request)))
  }

  trait SimilarityService {
    //Name of the similarity method.
    def name():String

    def similarity(request: SimilarityRequest): Future[SimilarityResult]
  }
}

trait ProductionSimilarityServiceComponent extends SimilarityServiceComponent { self: ConfigComponent =>
  //Word2Vec similarity service
  val modelFile = ConfigFactory.load().getString("word2vec.modelFile")
  val word2Vec = new Word2VecSimilarity(modelFile) with SimilarityService {
    def similarity(request: SimilarityRequest): Future[SimilarityResult] = {
      val confidence = similarity(request.text, request.hypothesis)
      Future.successful(SimilarityResult(request, name, confidence))
    }
  }
  //Bag-of-words similarity service.
  val bow = new WordOverlap() with SimilarityService {
    override def similarity(request: SimilarityRequest): Future[SimilarityResult] = {
      val confidence = similarity(request.text, request.hypothesis)
      Future.successful(SimilarityResult(request, name, confidence))
    }
  }
  //Add to similarity services.
  override protected val similarityServices = Seq(word2Vec, bow)
}
