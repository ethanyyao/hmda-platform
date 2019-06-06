package hmda.validation.engine

import hmda.model.filing.ts.TransmittalLar
import hmda.validation.context.ValidationContext
import hmda.validation.rules.lar.quality.Q600
import hmda.validation.rules.lar.syntactical.{S304, S305}

private[engine] object TsLarEngine2018
    extends ValidationEngine[TransmittalLar] {

  override def syntacticalChecks(ctx: ValidationContext) = Vector(
    S304,
    S305
  )

  override val qualityChecks = Vector(
    Q600
  )

}
