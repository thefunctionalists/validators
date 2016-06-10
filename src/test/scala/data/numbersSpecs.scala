package com.thefunctionalists.validators
package data

import org.specs2.Specification
import org.specs2.matcher.DisjunctionMatchers

import scalaz.{ \/, -\/, \/- }
object NumberSpecification
    extends Specification
    with NumbersFunctions
    with DisjunctionMatchers {

  def is =
    s2"""
       $positiveNumberValidator

    """

  implicit def success[T] = new Success[Error, T, \/]() {
    override def apply(t: T) = \/-(t)
  }

  def positiveNumberValidator = s2"""
 
   Positive number validator should
     pass for positive value ${PositiveNumber().`positive-value`}
     fail for negative value ${PositiveNumber().`negative-value`}

   """

  case class PositiveNumber() {
    implicit val failure = new Failure[Error, Int, \/]() {
      override def apply(t: Int) = -\/(NegativeIntegerError(t))
    }

    val validator = positiveIntValidator

    def `positive-value` = {
      validator(3) must be_\/-
    }

    def `negative-value` = {
      validator(-1) must be_-\/
    }
  }
}
