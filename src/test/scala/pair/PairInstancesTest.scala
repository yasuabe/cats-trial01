package pair

import cats._
import cats.data.Const
import cats.laws.discipline.{FoldableTests, FunctorTests, InvariantSemigroupalTests, InvariantTests, SemigroupalTests, UnorderedFoldableTests, UnorderedTraverseTests}
import cats.instances.int._
import cats.instances.double._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.funsuite.AnyFunSuite
import org.typelevel.discipline.scalatest.Discipline

class PairInvariantTests extends AnyFunSuite with Discipline {
  implicit val p: Invariant[Pair] = pairInvariant
  checkAll("Pair.InvariantLaws", InvariantTests[Pair].invariant[Int, Int, String])
}
class PairFunctorTests extends AnyFunSuite with Discipline {
  implicit val p: Functor[Pair] = pairFunctor
  checkAll("Pair.FunctorLaws", FunctorTests[Pair].functor[Int, Int, String])
}
class PairSemigroupalTests extends AnyFunSuite with Discipline {
  implicit val i0: Invariant[Pair]   = pairInvariant
  implicit val i1: Semigroupal[Pair] = pairSemigroupal
  checkAll("Pair.SemigroupalLaws", SemigroupalTests[Pair].semigroupal[Int, Int, String])
}
class PairInvariantSemigroupalTests extends AnyFunSuite with Discipline {
  implicit val i0: Invariant[Pair]            = pairInvariant
  implicit val i1: InvariantSemigroupal[Pair] = pairInvariantSemigroup
  checkAll("Pair.InvariantSemigroupalLaws", InvariantSemigroupalTests[Pair].invariantSemigroupal[Int, Int, String])
}
class PairUnorderedFoldableTests extends AnyFunSuite with Discipline {
  implicit val i1: UnorderedFoldable[Pair] = pairUnorderedFoldable
  checkAll("Pair.UnorderedFoldableLaws", UnorderedFoldableTests[Pair].unorderedFoldable[Int, Double])
}
class PairUnorderedTraverseTests extends AnyFunSuite with Discipline {
  implicit val i1: UnorderedTraverse[Pair] = pairUnorderedTraverse

  type IntConst[T] = Const[Int, T]
  implicit val arbXB: Arbitrary[IntConst[Int]] = Arbitrary[IntConst[Int]] {
    Gen.choose(-3, 3).map(Const[Int, Int])
  }
  checkAll(
    "Pair.UnorderedTraverseLaws",
    UnorderedTraverseTests[Pair].unorderedTraverse[Int, Int, Int, IntConst, IntConst])
}
class PairFoldableTests extends AnyFunSuite with Discipline {
  import cats.instances.option._
  implicit val i1: Foldable[Pair] = pairFoldable
  checkAll("Pair.FoldableLaws", FoldableTests[Pair].foldable[Int, Int])
}
