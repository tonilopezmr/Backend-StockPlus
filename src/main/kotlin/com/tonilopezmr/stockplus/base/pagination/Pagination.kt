package com.tonilopezmr.stockplus.base.pagination

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class Pagination(val page: Int, val size: Int) {
  companion object {
    fun of(page: Int, pageSize: Int) = if (pageSize == 0) {
      Pagination(page, Int.MAX_VALUE)
    } else {
      Pagination(page, pageSize)
    }
  }
}

infix fun Int.with(pageSize: Int): Pagination =
    Pagination.of(this, pageSize)

fun Pagination.request(): Pageable =
    PageRequest.of(page, size)
