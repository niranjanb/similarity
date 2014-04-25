package org.allenai.similarity.word2vec

import org.allenai.similarity.{Measures, Similarity}

class Word2VecSimilarity(modelFile:String) extends Similarity {

  val model = new Word2Vec()
  model.load(modelFile)

  override def similarity(x: String, y: String): Double = {
    val xtokens = x.split(" ").toList
    val ytokens = y.split(" ").toList
    val xvector = model.sumVector(xtokens)
    val yvector = model.sumVector(ytokens)
    Measures.cosine(xvector, yvector)
  }
}

object Word2VecSimilarityTest extends App {
  var x = "environment"
  var y = "ecosystem"
  val word2vec = new Word2VecSimilarity(args(0))
  println(s"Similarity(${x}, ${y})=${word2vec.similarity(x, y)}")
  println(s"Similarity(${x}, ${x})=${word2vec.similarity(x, x)}")
  println(s"Similarity(${y}, ${x})=${word2vec.similarity(y, x)}")
}
