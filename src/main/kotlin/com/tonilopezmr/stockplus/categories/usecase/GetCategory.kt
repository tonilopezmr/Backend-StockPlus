package com.tonilopezmr.stockplus.categories.usecase

import arrow.core.Either
import arrow.core.flatMap
import com.tonilopezmr.stockplus.categories.datasource.CategoryDataSource
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.categories.model.CategoryError
import org.springframework.stereotype.Component

@Component
class GetCategory(
    private val dataSource: CategoryDataSource
) {
  operator fun invoke(id: String): Either<CategoryError, Category> =
      dataSource.getById(id)
          .toEither { CategoryError.StorageError }
          .flatMap { it.toEither { CategoryError.NotFound } }
}