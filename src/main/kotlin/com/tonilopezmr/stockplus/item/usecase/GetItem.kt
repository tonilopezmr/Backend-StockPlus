package com.tonilopezmr.stockplus.item.usecase

import arrow.core.Either
import arrow.core.flatMap
import com.tonilopezmr.stockplus.item.datasource.StockItemDataSource
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component

@Component
class GetItem(
    private val itemDataSource: StockItemDataSource
) {
  operator fun invoke(id: String): Either<ItemError, StockItem> =
      itemDataSource.getById(id)
          .toEither { ItemError.StorageError }
          .flatMap { it.toEither { ItemError.NotFound } }
}