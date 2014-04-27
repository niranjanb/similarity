package org.allenai.similarity

/** A simple trait specifying a method for comparing two text fragments.
  */
trait Similarity {

  def similarity(x:String, y:String):Double

}

object WordOverlapTest extends App {
  var x = "brown dog"
  var y = "quick dog"
  val overlap = new WordOverlap
  println(s"Similarity(${x}, ${y})=${overlap.similarity(x, y)}")
  println(s"Similarity(${x}, ${x})=${overlap.similarity(x, x)}")
  println(s"Similarity(${y}, ${y})=${overlap.similarity(y, y)}")
}

class WordOverlap extends Similarity{

  override def similarity(x: String, y: String): Double = {
    def tokens(string:String) = string.split(" ").toSet
    val xtokens = tokens(x)
    val ytokens = tokens(y)
    Measures.jaccard(xtokens, ytokens)
  }

}
