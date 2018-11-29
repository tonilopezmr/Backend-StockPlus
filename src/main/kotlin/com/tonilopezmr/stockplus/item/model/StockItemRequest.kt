package com.tonilopezmr.stockplus.item.model

import com.tonilopezmr.stockplus.categories.model.Category

data class StockItemRequest(
    val name: String,
    val price: Double,
    val quantity: Int,
    val photo: List<String>,
    val category: String
)

fun StockItemRequest.toDomain(): StockItem = StockItem(
    id = "",
    name = name,
    price = price,
    quantity = quantity,
    photo = photo[0],
    category = Category(category, "")
)