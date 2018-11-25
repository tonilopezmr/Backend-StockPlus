package com.tonilopezmr.stockplus.categories.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.instances.list.foldable.nonEmpty
import com.tonilopezmr.stockplus.categories.datasource.CategoryDataSource
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.categories.model.CategoryError
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import com.tonilopezmr.stockplus.item.usecase.GetItems
import org.springframework.stereotype.Component

@Component
class DeleteCategory(
    private val categoryDataSource: CategoryDataSource,
    private val getCategory: GetCategory,
    private val getItems: GetItems
) {
  operator fun invoke(id: String): Either<CategoryError, Category> =
      getCategory(id).flatMap {category ->
        getItems.byCategory(category)
            .mapLeft { CategoryError.StorageError }
            .flatMap { delete(category, it) }
      }

  private fun delete(category: Category, items: List<StockItem>): Either<CategoryError, Category> =
      if(items.isNotEmpty()) {
         CategoryError.CategoryHasItems.left()
      } else {
        categoryDataSource.delete(category).toEither { CategoryError.StorageError }
      }
}