package tofu.internal
package carriers

trait MkFireCarrier2Macro {
  final implicit def interopCE2Carrier[I[_], F[_]]: MkFireCarrier2[I, F] =
  macro Interop
  .delegate2[MkFireCarrier2[I, F], I, F, { val `tofu.interop.CE2Kernel.mkFireBySync`: Unit }]
}

trait MkFireCarrier3Macro {
  final implicit def interopCE3Carrier[I[_], F[_]]: MkFireCarrier3[I, F] =
  macro Interop
  .delegate2[MkFireCarrier3[I, F], I, F, { val `tofu.interop.CE3Kernel.mkFireBySync`: Unit }]
}
