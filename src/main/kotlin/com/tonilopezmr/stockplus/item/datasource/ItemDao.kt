package com.tonilopezmr.stockplus.item.datasource

import com.tonilopezmr.stockplus.categories.datasource.CategoryEntity
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID

interface ItemDao : PagingAndSortingRepository<StockItemEntity, UUID> {
  fun findAllByCategory(category: CategoryEntity): List<StockItemEntity>
}