package org.allenai.similarity.implementations

import org.allenai.similarity.Measures

class WordOverlap extends Similarity{

  def tokens(string:String) = string.split(" ").toSet
  override def similarity(text: String, hypothesis: String): Double =
    Measures.jaccard(tokens(text), tokens(hypothesis))

}
