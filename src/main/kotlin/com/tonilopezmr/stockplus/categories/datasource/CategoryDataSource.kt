package com.tonilopezmr.stockplus.categories.datasource

import arrow.core.Option
import arrow.core.Try
import arrow.core.toOption
import com.tonilopezmr.stockplus.base.datatypes.TryLogger
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.base.pagination.request
import com.tonilopezmr.stockplus.categories.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryDataSource(private val categoryDao: CategoryDao) {

  fun all(pagination: Pagination): Try<List<Category>> = TryLogger(this::class) {
    categoryDao.findAll(pagination.request())
        .toList()
        .map(CategoryEntity::toDomain)
  }

  fun create(category: Category): Try<Category> = TryLogger(this::class) {
    categoryDao.save(category.toEntity()).toDomain()
  }

  fun getById(id: String): Try<Option<Category>> = TryLogger(this::class) {
    categoryDao.findById(id)
        .orElse(null)
        .toOption()
        .map { it.toDomain() }
  }
}
