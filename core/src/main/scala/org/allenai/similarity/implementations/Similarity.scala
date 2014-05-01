package org.allenai.similarity.implementations

trait Similarity {

  //Name of the similarity method.
  def name: String

  //Similarity between text and hypothesis strings.
  def similarity(text:String, hypothesis:String):Double
}
