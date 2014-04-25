package org.allenai.similarity

object Measures {

  def dice[T](x:Set[T], y:Set[T]) = x.intersect(y).size.toDouble/x.union(y).size

  def dotProduct[T <% Double](xcoll:Iterable[T], ycoll:Iterable[T]) = {
    require(xcoll.size == ycoll.size)
    (for{(x, y) <- xcoll zip ycoll} yield x * y).sum
  }

  def norm[T <% Double](coll:Iterable[T]): Double = math.sqrt(coll.map(x => x * x).sum)

  def cosine[T <% Double](xcoll:Iterable[T], ycoll:Iterable[T]):Double = {
    require(xcoll.size == ycoll.size)
    dotProduct(xcoll, ycoll)/(norm(xcoll)*norm(ycoll))
  }
}
object MeasuresTest extends App{
  val x = Seq(1,1,1)
  val y = Seq(2,2,2)
  println("Dot: " + Measures.cosine(x, y))
  println("Dot: " + Measures.cosine(x, x))
}
