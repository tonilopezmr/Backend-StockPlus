package com.tonilopezmr.stockplus.item.datasource

import org.springframework.data.repository.PagingAndSortingRepository

interface ItemDao : PagingAndSortingRepository<StockItemEntity, String>