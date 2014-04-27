package org.allenai.similarity

object Measures {

  def jaccard[T](x: Set[T], y:Set[T]) = {
    require(x.size + y.size > 0, "jaccard(x,y): x union y must be non-empty.")
    x.intersect(y).size.toDouble/x.union(y).size
  }

  def dice[T](x: Set[T], y: Set[T]) = {
    require(x.size + y.size > 0, "dice(x,y): x or y must be non-empty.")
    x.intersect(y).size.toDouble / (x.size + y.size)
  }

  def dotProduct[T <% Double](xcoll:Iterable[T], ycoll:Iterable[T]) = {
    require(xcoll.size == ycoll.size, "dotProduct(x, y): x and y must be of same length")
    (for{(x, y) <- xcoll zip ycoll} yield x * y).sum
  }

  def norm[T <% Double](coll:Iterable[T]): Double = math.sqrt(coll.map(x => x * x).sum)

  def cosine[T <% Double](xcoll:Iterable[T], ycoll:Iterable[T]):Double = {
    require(xcoll.size == ycoll.size, "cosine(x, y): x and y must be of same length.")
    dotProduct(xcoll, ycoll)/(norm(xcoll)*norm(ycoll))
  }
}
object MeasuresTest extends App{
  val x = Seq(1,1,1)
  val y = Seq(2,2,2)

  val a = Set("apple", "orange")
  val b = Set("apple", "pineapple", "guava")
  println(s"jaccard($a, $b) = ${Measures.jaccard(a, b)}")
  println(s"jaccard($a, $a) = ${Measures.jaccard(a, a)}")

  println(s"dice($a, $b) = ${Measures.dice(a, b)}")
  println(s"dice($a, $a) = ${Measures.dice(a, a)}")


  println(s"dot($x, $y) = ${Measures.dotProduct(x, y)}")
  println(s"dot($x, $x) = ${Measures.dotProduct(x, x)}")

  println(s"norm($x) = ${Measures.norm(x)}")
  println(s"norm($y) = ${Measures.norm(y)}")

  println(s"cosine($x, $y) =${Measures.cosine(x, y)}")
  println(s"cosine($x, $x) =${Measures.cosine(x, x)}")
}
