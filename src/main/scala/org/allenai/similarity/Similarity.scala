package org.allenai.similarity

/** A simple trait specifying a method for comparing two text fragments.
  */
trait Similarity {

  def similarity(x:String, y:String):Double

}

class WordOverlap extends Similarity{
  override def similarity(x: String, y: String): Double = {
    def tokens(string:String) = string.split(" ").toSet
    val xtokens = tokens(x)
    val ytokens = tokens(y)
    val intersection = xtokens.intersect(ytokens)
    val union = xtokens.intersect(ytokens)
    intersection.size.toDouble/union.size.toDouble
  }
}
