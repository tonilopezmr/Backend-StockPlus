package com.tonilopezmr.stockplus.item.usecase

import arrow.core.Either
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.item.datasource.StockItemDataSource
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component

@Component
class GetItems(private val itemDataSource: StockItemDataSource) {

  operator fun invoke(pagination: Pagination): Either<ItemError, List<StockItem>> =
      itemDataSource.all(pagination).toEither { ItemError.StorageError }

  fun byCategory(category: Category): Either<ItemError, List<StockItem>> =
      itemDataSource.getByCategory(category).toEither { ItemError.StorageError }
}