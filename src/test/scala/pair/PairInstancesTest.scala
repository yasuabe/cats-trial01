package pair

import cats._
import cats.data.Const
import cats.laws.discipline._
import cats.instances.int._
import cats.instances.double._
import cats.instances.string._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.funsuite.AnyFunSuite
import org.typelevel.discipline.scalatest.Discipline

// -----------------------------------------------------------------------------------------------------------------
class PairInvariantTests extends AnyFunSuite with Discipline {
  implicit val p: Invariant[Pair] = new PairInvariant {}
  checkAll("Pair.InvariantLaws", InvariantTests[Pair].invariant[Int, Int, String])
}
class PairFunctorTests extends AnyFunSuite with Discipline {
  implicit val p: Functor[Pair] = new PairFunctor {}
  checkAll("Pair.FunctorLaws", FunctorTests[Pair].functor[Int, Int, String])
}
// -----------------------------------------------------------------------------------------------------------------
class PairSemigroupalTests extends AnyFunSuite with Discipline {
  implicit val i0: Invariant[Pair]   = new PairInvariant {}
  implicit val i1: Semigroupal[Pair] = new PairSemigroupal {}
  checkAll("Pair.SemigroupalLaws", SemigroupalTests[Pair].semigroupal[Int, Int, String])
}
class PairInvariantSemigroupalTests extends AnyFunSuite with Discipline {
  implicit val i1: InvariantSemigroupal[Pair] = new PairInvariantSemigroupal {}
  checkAll("Pair.InvariantSemigroupalLaws", InvariantSemigroupalTests[Pair].invariantSemigroupal[Int, Int, String])
}
class PairInvariantMonoidalTests extends AnyFunSuite with Discipline {
  implicit val i1: InvariantMonoidal[Pair] = new PairInvariantMonoidal {}
  checkAll("Pair.InvariantMonoialLaws", InvariantMonoidalTests[Pair].invariantMonoidal[Int, Int, String])
}
// -----------------------------------------------------------------------------------------------------------------
class PairUnorderedFoldableTests extends AnyFunSuite with Discipline {
  implicit val i1: UnorderedFoldable[Pair] = new PairUnorderedFoldable {}
  checkAll("Pair.UnorderedFoldableLaws", UnorderedFoldableTests[Pair].unorderedFoldable[Int, Double])
}
class PairUnorderedTraverseTests extends AnyFunSuite with Discipline {
  implicit val i1: UnorderedTraverse[Pair] = new PairUnorderedTraverse {}

  type IntConst[T] = Const[Int, T]
  implicit val arbXB: Arbitrary[IntConst[Int]] = Arbitrary[IntConst[Int]] {
    Gen.choose(-3, 3).map(Const[Int, Int])
  }
  checkAll(
    "Pair.UnorderedTraverseLaws",
    UnorderedTraverseTests[Pair].unorderedTraverse[Int, Int, Int, IntConst, IntConst])
}
// -----------------------------------------------------------------------------------------------------------------
class PairApplyTests extends AnyFunSuite with Discipline {
  implicit val i0: Apply[Pair] = new PairApply {}
  checkAll("Pair.ApplyLaws", ApplyTests[Pair].apply[Int, Int, String])
}
class PairCommutativeApplyTests extends AnyFunSuite with Discipline {
  implicit val i0: CommutativeApply[Pair] = new PairCommutativeApply {}
  checkAll("Pair.CommutativeApplyLaws", CommutativeApplyTests[Pair].commutativeApply[Int, Int, String])
}
class PairFlatMapTests extends AnyFunSuite with Discipline {
  implicit val i0: FlatMap[Pair] = new PairFlatMap {}
  checkAll("Pair.FlatMapLaws", FlatMapTests[Pair].flatMap[Int, Int, String])
}
class PairCommutativeFlatMapTests extends AnyFunSuite with Discipline {
  implicit val i0: CommutativeFlatMap[Pair] = new PairCommutativeFlatMap {}
  checkAll("Pair.CommutativeFlatMapLaws", CommutativeFlatMapTests[Pair].commutativeFlatMap[Int, Int, String])
}
class PairApplicativeTests extends AnyFunSuite with Discipline {
  implicit val i0: Applicative[Pair] = new PairApplicative {}
  checkAll("Pair.ApplicativeLaws", ApplicativeTests[Pair].applicative[Int, Int, String])
}
class PairCommutativeApplicativeTests extends AnyFunSuite with Discipline {
  implicit val i0: CommutativeApplicative[Pair] = new PairCommutativeApplicative {}
  checkAll("Pair.CommutativeApplicativeLaws", CommutativeApplicativeTests[Pair].commutativeApplicative[Int, Int, String])
}
class PairMonadTests extends AnyFunSuite with Discipline {
  implicit val i0: Monad[Pair] = new PairMonad {}
  checkAll("Pair.MonadLaws", MonadTests[Pair].monad[Int, Int, String])
}
class PairCommutativeMonadTests extends AnyFunSuite with Discipline {
  implicit val i0: CommutativeMonad[Pair] = new PairCommutativeMonad {}
  checkAll("Pair.CommutativeMonadLaws", CommutativeMonadTests[Pair].commutativeMonad[Int, Int, String])
}
class PairCommutativeMonad2Tests extends AnyFunSuite with Discipline {
  implicit val i0: CommutativeMonad[Pair] = new PairCommutativeMonad2 {}
  checkAll("Pair.CommutativeMonadLaws", CommutativeMonadTests[Pair].commutativeMonad[Int, Int, String])
}
class PairCoflatMapTests extends AnyFunSuite with Discipline {
  implicit val i1: CoflatMap[Pair] = new PairCoflatMap {}
  checkAll("Pair.CoflatMapLaws", CoflatMapTests[Pair].coflatMap[Int, Int, Int])
}
//class PairComonadTests extends AnyFunSuite with Discipline {
//  implicit val i1: Comonad[Pair] = new PairComonad {}
//  checkAll("Pair.ComonadLaws", ComonadTests[Pair].comonad[Int, Int, Int])
//}
class PairDistributiveTests extends AnyFunSuite with Discipline {
  import cats.instances.option._
  implicit val i1: Distributive[Pair] = new PairDistributive {}
  checkAll(
    "Pair.DistributiveLaws",
    DistributiveTests[Pair].distributive[Int, Int, Int, Option, Pair])
}

class PairFoldableTests extends AnyFunSuite with Discipline {
  import cats.instances.option._
  implicit val i1: Foldable[Pair] = new PairFoldable {}
  checkAll("Pair.FoldableLaws", FoldableTests[Pair].foldable[Int, Int])
}
class PairReducibleTests extends AnyFunSuite with Discipline {
  import cats.instances.option._
  import cats.instances.unit._
  implicit val i1: Reducible[Pair] = new PairReducible {}
  checkAll("Pair.ReducibleLaws", ReducibleTests[Pair].reducible[Option, Int, Int])
}
class PairTraverseTests extends AnyFunSuite with Discipline {
  import cats.instances.option._
  type IntConst[T] = Const[Int, T]
  implicit val arbXB: Arbitrary[IntConst[Int]] = Arbitrary[IntConst[Int]] {
    Gen.choose(-3, 3).map(Const[Int, Int])
  }
  implicit val i1: Traverse[Pair] = new PairTraverse {}
  checkAll("Pair.TraverseLaws", TraverseTests[Pair].traverse[Int, Int, Int, Int, IntConst, IntConst])
}
class PairNonEmptyTraverseTests extends AnyFunSuite with Discipline {
  import cats.instances.option._
  import cats.instances.unit._
  type IntConst[T] = Const[Int, T]
  implicit val arbXB: Arbitrary[IntConst[Int]] = Arbitrary[IntConst[Int]] {
    Gen.choose(-3, 3).map(Const[Int, Int])
  }
  implicit val i1: NonEmptyTraverse[Pair] = new PairNonEmptyTraverse {}
  checkAll(
    "Pair.NonEmptyTraverseLaws",
    NonEmptyTraverseTests[Pair].nonEmptyTraverse[Option, Int, Int, Int, Int, IntConst, IntConst])
}
class PairSemigroupKTests extends AnyFunSuite with Discipline {
  implicit val i1: SemigroupK[Pair] = new PairSemigroupK {}
  checkAll("Pair.SemigroupKLaws", SemigroupKTests[Pair].semigroupK[Int])
}

