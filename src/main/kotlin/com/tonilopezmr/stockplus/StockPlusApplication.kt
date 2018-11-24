package com.tonilopezmr.stockplus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockPlusApplication

fun main(args: Array<String>) {
  runApplication<StockPlusApplication>(*args)
}
