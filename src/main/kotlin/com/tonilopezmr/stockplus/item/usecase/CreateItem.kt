package com.tonilopezmr.stockplus.item.usecase

import arrow.core.Either
import arrow.core.flatMap
import com.tonilopezmr.stockplus.categories.datasource.CategoryDataSource
import com.tonilopezmr.stockplus.categories.usecase.GetCategory
import com.tonilopezmr.stockplus.item.datasource.StockItemDataSource
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component

@Component
class CreateItem(
    private val itemDataSource: StockItemDataSource,
    private val getCategory: GetCategory
) {

  operator fun invoke(stockItem: StockItem): Either<ItemError, StockItem> =
      getCategory(stockItem.category.id)
          .mapLeft { ItemError.CategoryNotExists }
          .flatMap { itemDataSource.create(stockItem).toEither { ItemError.StorageError } }

}