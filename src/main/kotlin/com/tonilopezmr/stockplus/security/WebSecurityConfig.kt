package com.tonilopezmr.stockplus.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
    var authEntryPoint: MyAuthEntryPoint
) : WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        .antMatchers("/v2/api-docs/**").permitAll()
        .antMatchers("/swagger**/**").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .headers().frameOptions().sameOrigin()
        .and().httpBasic().authenticationEntryPoint(authEntryPoint)
        .and().csrf().disable()
        .exceptionHandling()
  }

  @Autowired
  @Throws(Exception::class)
  fun configureGlobal(auth: AuthenticationManagerBuilder) {
    auth.inMemoryAuthentication()
        .withUser("saymyname")
        .password("{noop}youarejohn")
        .roles("USER")
  }
}
