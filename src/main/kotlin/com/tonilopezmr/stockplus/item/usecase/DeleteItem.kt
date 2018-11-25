package com.tonilopezmr.stockplus.item.usecase

import arrow.core.Either
import arrow.core.Option
import arrow.core.flatMap
import com.tonilopezmr.stockplus.item.datasource.StockItemDataSource
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component

@Component
class DeleteItem(private val dataSource: StockItemDataSource) {
  operator fun invoke(id: String): Either<ItemError, StockItem> =
      dataSource.getById(id)
          .toEither { ItemError.StorageError }
          .flatMap(::delete)

  private fun delete(item: Option<StockItem>): Either<ItemError, StockItem> =
      item.toEither { ItemError.NotFound }
          .flatMap {
            dataSource.delete(it).toEither { ItemError.StorageError }
          }
}