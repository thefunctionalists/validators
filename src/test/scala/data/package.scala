package com.thefunctionalists.validators

package data {

  trait Error {
    val message: String
  }

  case object EmptyStringError extends Error {
    val message = "Empty string"
  }

  case class NonEmptyStringError(t: String) extends Error {
    val message = s"NonEmpty string $t"
  }

  case class NegativeIntegerError(t: Int) extends Error {
    val message = s"Negative int $t"
  }

  case class NegativeDoubleError(t: Double) extends Error {
    val message = s"Negative double $t"
  }
}
