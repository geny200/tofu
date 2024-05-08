package tofu.internal.carriers

import tofu.{Fire, MakeFire}

trait MkFireCarrier2[I[_], F[_]] extends MakeFire[I, F]

object MkFireCarrier2 extends MkFireCarrier2Macro

trait MkFireCarrier3[I[_], F[_]] extends MakeFire[I, F]

object MkFireCarrier3 extends MkFireCarrier3Macro
