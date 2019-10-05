import cats.kernel.CommutativeMonoid
import cats.{CommutativeApplicative, Contravariant, Eq, Eval, Foldable, Functor, Invariant, InvariantSemigroupal, Semigroupal, Traverse, UnorderedFoldable, UnorderedTraverse}

package object pair {
  type Pair[A] = (A, A)

  implicit def eqPair[A]: Eq[Pair[A]] = Eq.fromUniversalEquals

  val pairInvariant: Invariant[Pair] = new Invariant[Pair] {
    def imap[A, B](fa: (A, A))(f: A => B)(g: B => A): (B, B) = (f(fa._1), f(fa._2))
  }
  val pairFunctor: Functor[Pair] = new Functor[Pair] {
    def map[A, B](fa: Pair[A])(f: A => B): (B, B) = (f(fa._1), f(fa._2))
  }
  // Contravariant[Pair]
  val pairSemigroupal: Semigroupal[Pair] = new Semigroupal[Pair] {
    def product[A, B](fa: (A, A), fb: (B, B)): ((A, B), (A, B)) = ((fa._1, fb._1), (fa._2, fb._2))
  }
  // ContravariantSemigroupal[Pair]
  // ContravariantMonoidal[Pair]

  def pairInvariantSemigroup: InvariantSemigroupal[Pair] = new InvariantSemigroupal[Pair] {
   def product[A, B](fa: (A, A), fb: (B, B)): ((A, B), (A, B)) = pairSemigroupal.product(fa, fb)
   def imap[A, B](fa: (A, A))(f: A => B)(g: B => A): (B, B) = pairInvariant.imap(fa)(f)(g)
  }
  def pairUnorderedFoldable: UnorderedFoldable[Pair] = new UnorderedFoldable[Pair] {
    def unorderedFoldMap[A, B](fa: (A, A))(f: A => B)(implicit cm: CommutativeMonoid[B]): B =
      cm.combine(f(fa._1), f(fa._2))
  }
  def pairUnorderedTraverse: UnorderedTraverse[Pair] = new UnorderedTraverse[Pair] {
    def unorderedTraverse[G[_], A, B](sa: (A, A))(f: A => G[B])(implicit ca: CommutativeApplicative[G]): G[(B, B)] =
      ca.product(f(sa._1), f(sa._2))
    def unorderedFoldMap[A, B](fa: (A, A))(f: A => B)(implicit cm: CommutativeMonoid[B]): B =
      pairUnorderedFoldable.unorderedFoldMap(fa)(f)
  }
  def pairFoldable: Foldable[Pair] = new Foldable[Pair] {
    def foldLeft[A, B](fa: (A, A), b: B)(f: (B, A) => B): B =
      f(f(b, fa._1), fa._2)
    def foldRight[A, B](fa: (A, A), lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
      Eval.defer(f(fa._1, Eval.defer(f(fa._2, lb))))
  }
}
