object Even {
  def unapply(s: String): Boolean = s.length % 2 == 0
}
"even" match {
  case s @ Even() => println(s"$s has an even number of characters")
  case s          => println(s"$s has an odd number of characters")
}
class Nat(val n: Int) {
  def get: Int = n
  def isEmpty: Boolean = n < 0
}
object Nat {
  def unapply(n: Int): Nat = new Nat(n)
}
5 match {
  case Nat(n) => println(s"$n is a natural number")
  case _      => ()
}
object CharList {
  def unapplySeq(s: String): Some[List[Char]] = Some(s.toList)
  // def unapplySeq(s: String): CharList = CharList(s) //Some(s.toList)
}
"example" match {
  case CharList(c1, c2, c3, c4, _, _, _) =>
    println(s"$c1,$c2,$c3,$c4")
  case _ =>
    println("Expected *exactly* 7 characters!")
}

@volatile lazy val xs: List[String] = List("d", "o", "t", "t", "y")
@volatile lazy val ys: List[Char] = "scala".toCharArray.toList

def foo(s: String, ch: Char): String = s + ch
(xs zip ys).map { (foo _).tupled }.foreach(println)

// import scala.language.strictEquality

println(2 == "2")