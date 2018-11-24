package com.tonilopezmr.stockplus.base.datatypes

import arrow.core.Try
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.reflect.KClass

class TryLogger {

  companion object {
    operator fun <A, B : Any> invoke(tag: KClass<B>, f: () -> A): Try<A> = Try(f).apply {
      fold({ Logger.getLogger(tag.qualifiedName).log(Level.SEVERE, it.message, it) }, { Unit })
    }
  }
}