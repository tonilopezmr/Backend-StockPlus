package com.tonilopezmr.stockplus.item.model

import com.tonilopezmr.stockplus.categories.Category

data class StockItem(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: Category
)