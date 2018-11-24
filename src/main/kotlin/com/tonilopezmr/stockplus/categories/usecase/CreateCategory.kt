package com.tonilopezmr.stockplus.categories.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import com.tonilopezmr.stockplus.categories.datasource.CategoryDataSource
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.categories.model.CategoryError
import org.springframework.stereotype.Component

@Component
class CreateCategory(
    private val dataSource: CategoryDataSource
) {
  operator fun invoke(category: Category): Either<CategoryError, Category> =
      validCategory(category).flatMap {
        dataSource.create(category).toEither { CategoryError.StorageError }
      }


  private fun validCategory(category: Category): Either<CategoryError, Category> =
      if (category.name.isEmpty() || category.name.isBlank()) {
        CategoryError.EmptyName.left()
      } else {
        category.right()
      }
}