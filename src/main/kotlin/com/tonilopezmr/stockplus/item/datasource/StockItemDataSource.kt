package com.tonilopezmr.stockplus.item.datasource

import arrow.core.Try
import com.tonilopezmr.stockplus.base.datatypes.TryLogger
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.base.pagination.request
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component

@Component
class StockItemDataSource(private val itemDao: ItemDao) {

  fun all(pagination: Pagination): Try<List<StockItem>> = TryLogger(this::class) {
    itemDao.findAll(pagination.request())
        .toList()
        .map(StockItemEntity::toDomain)
  }

  fun create(stockItem: StockItem): Try<StockItem> = TryLogger(this::class) {
    itemDao.save(stockItem.toEntity()).toDomain()
  }

}