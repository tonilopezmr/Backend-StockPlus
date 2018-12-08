package com.tonilopezmr.stockplus.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class MyAuthEntryPoint : BasicAuthenticationEntryPoint() {

  override fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
    response.addHeader("WWW-Authenticate", "Basic realm=$realmName")
    response.status = HttpServletResponse.SC_UNAUTHORIZED
    val writer = response.writer
    writer.println("HTTP Status 401 - " + authEx.message)
  }

  override fun afterPropertiesSet() {
    realmName = "DeveloperStack"
    super.afterPropertiesSet()
  }

}