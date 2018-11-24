package com.tonilopezmr.stockplus.item.model

import com.tonilopezmr.stockplus.categories.Category

data class StockItemRequest(
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: String
)

fun StockItemRequest.toDomain(): StockItem = StockItem(
    id = "",
    name = name,
    price = price,
    quantity = quantity,
    category = Category(category, "")
)