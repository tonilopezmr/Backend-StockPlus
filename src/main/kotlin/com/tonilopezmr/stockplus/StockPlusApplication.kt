package com.tonilopezmr.stockplus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@SpringBootApplication
class StockPlusApplication : WebMvcAutoConfiguration() {

  @Bean
  fun corsFilter(): CorsFilter {
    val source = UrlBasedCorsConfigurationSource()
    val config = CorsConfiguration()
    config.allowCredentials = true
    config.addAllowedOrigin("http://localhost:3000")
    config.addAllowedHeader("*")
    config.addExposedHeader("X-Total-Count")
    config.addExposedHeader("Content-Range")
    config.addExposedHeader("Content-Type")
    config.addExposedHeader("Accept")
    config.addExposedHeader("X-Requested-With")
    config.addExposedHeader("remember-me")
    config.addAllowedMethod("*")
    source.registerCorsConfiguration("/**", config)
    return CorsFilter(source)
  }

}

fun main(args: Array<String>) {
  runApplication<StockPlusApplication>(*args)
}
