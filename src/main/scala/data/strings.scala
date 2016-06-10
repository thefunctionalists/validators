package com.thefunctionalists.validators
package data

class NonEmptyStringValidator[L, T[_, _]](implicit s: Success[L, String, T], f: Failure[L, String, T])
  extends BaseValidator[L, String, T](_.trim.nonEmpty)

class EmptyStringValidator[L, T[_, _]](implicit s: Success[L, String, T], f: Failure[L, String, T])
  extends BaseValidator[L, String, T](_.trim.isEmpty)

trait StringsFunctions {

  def nonEmptyString[L, T[_, _]](implicit s: Success[L, String, T], f: Failure[L, String, T]) = new NonEmptyStringValidator()

  def emptyString[L, T[_, _]](implicit s: Success[L, String, T], f: Failure[L, String, T]) = new EmptyStringValidator()
}
