package com.thefunctionalists.validators
package data

import java.util.regex.Pattern

trait EmailValidatorPattern[T] {
  def validate: T => Boolean

  lazy val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  lazy val pattern = Pattern.compile(EMAIL_PATTERN)

}

class EmailValidator[L, R, T[_, _]](implicit v: EmailValidatorPattern[R], s: Success[L, R, T], f: Failure[L, R, T]) extends BaseValidator[L, R, T](s => v.validate(s))
