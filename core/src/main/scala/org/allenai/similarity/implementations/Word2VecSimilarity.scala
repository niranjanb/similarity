package org.allenai.similarity.implementations

import org.allenai.similarity.Measures

class Word2VecSimilarity(modelFile:String) extends Similarity{

  override val name = "Word2Vec-Cosine"

  val word2Vec = new Word2Vec()
  word2Vec.load(modelFile)

  /** Similarity between the text and hypothesis as the cosine of the angle between their vector representations.
    *
    * 1. Convert text and hypothesis into tokens.
    * 2. Use Word2Vec to create a single vector representation of text (and hypothesis)
    * by adding the vector representations of all tokens.
    * 3. Return cosine of the summed vectors.
    *
    * TODO Add IDF-like weights for tokens so tokens like "a" don't contribute a lot to the cosine.
    *
    */
  override def similarity(text: String, hypothesis: String): Double = {
    val textTokens = text.toLowerCase.split(" ").filter(word => word2Vec.contains(word)).toList
    val hypothesisTokens = hypothesis.toLowerCase.split(" ").filter(word => word2Vec.contains(word)).toList
    val textVector = word2Vec.sumVector(textTokens)
    val hypothesisVector = word2Vec.sumVector(hypothesisTokens)
    Measures.cosine(textVector, hypothesisVector)
  }
}
