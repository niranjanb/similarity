package org.allenai.similarity

import org.allenai.similarity.services._

import com.typesafe.config.{ Config, ConfigFactory }
import org.allenai.similarity.services.{ProductionSimilarityServiceComponent, SimilarityServiceComponent}

/** Provides top-level services necessary to run the application.
  */
trait Context
    extends ConfigComponent
    with SimilarityServiceComponent

/** Provides a [[com.typesafe.config.Config]] and accessors to values */
trait ConfigComponent {
  def config: Config

  /** Direct accessors to required config values */
  object ConfigValues {
    private def configKey(key: String) = s"org.allenai.similarity.webapp.$key"
    val port = config.getInt(configKey("port"))
    val entailmentServiceUrl = config.getString(configKey("entailment-service.url"))
  }
}

trait ProductionContext
    extends Context
    with ProductionSimilarityServiceComponent {

  override val config = ConfigFactory.load
}
