package org.allenai.similarity.implementations

import org.allenai.similarity.Measures

class Word2VecSimilarity(modelFile:String) extends Similarity{
  val word2Vec = new Word2Vec()
  word2Vec.load(modelFile)

  override def similarity(text: String, hypothesis: String): Double = {
    val textTokens = text.toLowerCase.split(" ").filter(word => word2Vec.contains(word)).toList
    val hypothesisTokens = hypothesis.toLowerCase.split(" ").filter(word => word2Vec.contains(word)).toList
    val textVector = word2Vec.sumVector(textTokens)
    val hypothesisVector = word2Vec.sumVector(hypothesisTokens)
    Measures.cosine(textVector, hypothesisVector)
  }
}
