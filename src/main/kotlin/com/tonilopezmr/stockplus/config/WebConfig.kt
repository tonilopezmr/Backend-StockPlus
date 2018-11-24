package com.tonilopezmr.stockplus.config


import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig(
    private val env: Environment
) : WebMvcConfigurer {
  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**")
        .allowedOrigins(env.getProperty("cors.allowedOrigin"))
        .allowedMethods("GET", "POST", "PUT", "DELETE")
  }
}
