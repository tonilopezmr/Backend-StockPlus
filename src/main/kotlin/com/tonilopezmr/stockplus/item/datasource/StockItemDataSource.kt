package com.tonilopezmr.stockplus.item.datasource

import arrow.core.Option
import arrow.core.Try
import arrow.core.toOption
import com.tonilopezmr.stockplus.base.datatypes.TryLogger
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.base.pagination.request
import com.tonilopezmr.stockplus.categories.datasource.toDomain
import com.tonilopezmr.stockplus.categories.datasource.toEntity
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.item.model.StockItem
import org.springframework.stereotype.Component
import java.util.UUID

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

  fun getByCategory(category: Category): Try<List<StockItem>> = TryLogger(this::class) {
    itemDao.findAllByCategory(category.toEntity())
        .toList()
        .map(StockItemEntity::toDomain)
  }

  fun getById(id: String): Try<Option<StockItem>> = TryLogger(this::class) {
    itemDao.findById(UUID.fromString(id))
        .orElse(null)
        .toOption()
        .map { it.toDomain() }
  }

  fun delete(stockItem: StockItem): Try<StockItem> = TryLogger(this::class) {
    itemDao.delete(stockItem.toEntity())
    stockItem
  }
}