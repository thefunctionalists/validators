package com.thefunctionalists.validators
package data

import org.specs2.Specification
import org.specs2.matcher.DisjunctionMatchers

import scalaz.{ -\/, \/, \/- }

object NonEmptyStringsSpecs
    extends Specification
    with StringsFunctions
    with DisjunctionMatchers {
  def is =
    s2"""
     $nonEmpty
     $empty
   """

  def nonEmpty =
    s2"""
    Non empty validator should
      fail on empty string ${NonEmptyString().`empty`}
      fail on empty string with spaces ${NonEmptyString().`empty-with-spaces`}

      pass on non empty string ${NonEmptyString().`non-empty`}
      pass on non empty string with spaces ${NonEmptyString().`non-empty-with-spaces`}
    """

  def empty =
    s2"""
    Empty validator should
      fail on non empty string ${NonEmptyString().`non-empty`}
      fail on non empty string with spaces ${NonEmptyString().`non-empty-with-spaces`}

      pass on empty string ${NonEmptyString().`empty`}
      pass on empty string with spaces ${NonEmptyString().`empty-with-spaces`}
    """

  implicit def success[T] = new Success[Error, T, \/] {
    override def apply(t: T) = \/-(t)
  }

  case class NonEmptyString() {
    implicit val failure = new Failure[Error, String, \/] {
      override def apply(s: String): \/[Error, String] = -\/(EmptyStringError)
    }

    val validator = new NonEmptyStringValidator()

    def `non-empty` = {
      validator("non-empty") must be_\/-
    }

    def empty = {
      validator("") must be_-\/
    }

    def `empty-with-spaces` = {
      validator("   ") must be_-\/
    }

    def `non-empty-with-spaces` = {
      validator(" non-empty ") must be_\/-
    }

  }

  case class EmptyString() {
    implicit val failure = new Failure[Error, String, \/] {
      override def apply(s: String) = -\/(NonEmptyStringError(s))
    }

    val validator = new EmptyStringValidator()

    def `non-empty` = {
      validator("non-empty") must be_-\/
    }

    def empty = {
      validator("") must be_\/-
    }

    def `empty-with-spaces` = {
      validator("   ") must be_\/-
    }

    def `non-empty-with-spaces` = {
      validator(" non-empty ") must be_-\/
    }
  }
}
