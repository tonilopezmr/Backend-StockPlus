package com.tonilopezmr.stockplus.item.usecase

import arrow.core.Either
import com.tonilopezmr.stockplus.item.datasource.StockItemDataSource
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component

@Component
class CreateItem(private val itemDataSource: StockItemDataSource) {

  operator fun invoke(stockItem: StockItem): Either<ItemError, StockItem> =
      itemDataSource.create(stockItem).toEither { ItemError.StorageError }

}