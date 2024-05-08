package tofu.internal
package instances

import tofu.internal.carriers.{MkFireCarrier2, MkFireCarrier3}

private[tofu] trait MakeFireInstance extends MakeFireInstances0 {
  final implicit def byCarrierCE3[I[_], F[_], R[_[_], _]](implicit
      carrier: MkFireCarrier3[I, F]
  ): MkFireCarrier3[I, F] = carrier
}

private[tofu] trait MakeFireInstances0 {
  final implicit def byCarrierCE2[I[_], F[_], R[_[_], _]](implicit
      carrier: MkFireCarrier2[I, F]
  ): MkFireCarrier2[I, F] = carrier
}
