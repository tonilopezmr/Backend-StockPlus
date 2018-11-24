package com.tonilopezmr.stockplus.base

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <A> success(value: A): ResponseEntity<A> = ResponseEntity.ok(value)
fun <A> created(value: A): ResponseEntity<A> = ResponseEntity(value, HttpStatus.CREATED)