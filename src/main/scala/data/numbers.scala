package com.thefunctionalists.validators
package data

class PositiveNumberValidator[L, R, T[_, _]](implicit zero: Zero[R], odr: R => Ordered[R], s: Success[L, R, T], f: Failure[L, R, T])
  extends BaseValidator[L, R, T](s => s > zero.zero)

trait Zero[T] {
  def zero: T
}

trait NumbersFunctions {

  private implicit val intZero = new Zero[Int] {
    override def zero: Int = 0
  }

  private implicit val doubleZero = new Zero[Double] {
    override def zero: Double = 0.0
  }

  private implicit val longZero = new Zero[Long] {
    override def zero: Long = 0L
  }

  def positiveIntValidator[L, T[_, _]](implicit s: Success[L, Int, T], f: Failure[L, Int, T]) = new PositiveNumberValidator[L, Int, T]

  def positiveDoubleValidator[L, T[_, _]](implicit s: Success[L, Double, T], f: Failure[L, Double, T]) = new PositiveNumberValidator[L, Double, T]

  def positiveLongValidator[L, T[_, _]](implicit s: Success[L, Long, T], f: Failure[L, Long, T]) = new PositiveNumberValidator[L, Long, T]
}
