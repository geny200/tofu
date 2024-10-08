package tofu.logging

import zio.{Has, UIO, URIO}
import tofu.higherKind.Embed
import zio.ZIO
import zio.interop.catz.*

package object zlogs {
  type ZLogs[R]    = Logs[UIO, URIO[R, *]]
  type ZLogging[R] = Logging[URIO[R, *]]
  type ULogging    = Logging[UIO]

  type ZLog[R] = Has[ZLogging[R]]
  type ULog    = Has[ZLogging[Any]]

  type TofuLogging = Has[ULogging]
  type TofuLogs    = Has[ZLogging.Make]

  val TofuLogging: Logging[URIO[TofuLogging, *]] =
    Embed[Logging].embed(ZIO.access[TofuLogging](_.get.widen))

  val TofuLogs: Logging.Make[URIO[TofuLogs, *]] =
    Embed[Logging.Make].embed(ZIO.access[TofuLogs](_.get.biwiden))

  object implicits {
    implicit def tofuLogImplicit[R <: TofuLogging, E]: Logging[ZIO[R, E, *]]    = TofuLogging.widen
    implicit def tofuLogsImplicit[R <: TofuLogs, E]: Logging.Make[ZIO[R, E, *]] = TofuLogs.biwiden
  }
}
