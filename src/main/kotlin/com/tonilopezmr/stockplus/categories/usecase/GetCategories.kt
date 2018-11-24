package com.tonilopezmr.stockplus.categories.usecase

import arrow.core.Either
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.categories.datasource.CategoryDataSource
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.categories.model.CategoryError
import org.springframework.stereotype.Component

@Component
data class GetCategories(
  private val dataSource: CategoryDataSource
) {
  operator fun invoke(pagination: Pagination): Either<CategoryError, List<Category>> =
      dataSource.all(pagination).toEither { CategoryError.StorageError }
}