package com.thefunctionalists.validators

package object data {

  abstract class Result[L, R, T[_, _]] {
    def apply(r: R): T[L, R]
  }

  abstract class Success[L, R, T[_, _]] extends Result[L, R, T]

  abstract class Failure[L, R, T[_, _]] extends Result[L, R, T]

  abstract class BaseValidator[L, R, T[_, _]](test: R => Boolean)(implicit s: Success[L, R, T], f: Failure[L, R, T]) {
    def apply(t: R): T[L, R] = if (test(t)) s.apply(t) else f.apply(t)
  }

}

