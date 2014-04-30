package org.allenai.similarity.implementations

trait Similarity {

  def similarity(text:String, hypothesis:String):Double
}
