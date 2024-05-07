package tofu.logging

import cats.Monad
import tofu.Errors
import tofu.higherKind.Mid

/** Logging middleware Alg[LoggingMid] is a special form of implicit evidence of injectable logging support generally
  * you don't need `Logging` instance to derive this so choice of logging postponed until this middleware is attached to
  * the core instance
  */
abstract class LoggingMid[A] {
  def around[F[_]: Monad: Logging](fa: F[A]): F[A]

  def toMid[F[_]: Monad: Logging]: Mid[F, A] = around(_)
}

object LoggingMid extends builder.LoggingMidBuilder.DefaultImpl with LoggingMidMacroInstances {
  type Result[A] = LoggingMid[A]

  type Of[U[_[_]]] = U[LoggingMid]
}

/** Logging middleware supporting error reporting Alg[LoggingErrMid[E, _] ] is a special form of implicit evidence of
  * injectable logging support generally you don't need `Logging` instance to derive this so choice of logging postponed
  * until this middleware is attached to the core instance
  */
abstract class LoggingErrMid[E, A] extends LoggingMid[A] {
  def aroundErr[F[_]: Monad: ({ type L[x[_]] = Errors[x, E] })#L: Logging](fa: F[A]): F[A]

  def around[F[_]: Monad: Logging](fa: F[A]): F[A] = around(fa)

  def toMid[F[_]: Monad: ({ type L[x[_]] = Errors[x, E] })#L: Logging]: Mid[F, A] = aroundErr(_)
}

object LoggingErrMid {
  type Of[U[_[_]], E] = U[LoggingErrMid[E, _]]
  type Try[U[_[_]]]   = U[LoggingErrMid[Throwable, _]]

  object Try extends builder.LoggingErrMidBuilder.DefaultImpl[Throwable]
}
