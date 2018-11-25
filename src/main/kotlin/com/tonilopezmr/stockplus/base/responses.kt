package com.tonilopezmr.stockplus.base

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

val headers = HttpHeaders()

fun <A> success(value: A): ResponseEntity<A> = ResponseEntity.ok(value)
fun <A> success(value: List<A>): ResponseEntity<List<A>> =
    ResponseEntity(value, headers.also { it.set("X-Total-Count", value.size.toString()) }, HttpStatus.OK)
fun <A> created(value: A): ResponseEntity<A> = ResponseEntity(value, HttpStatus.CREATED)