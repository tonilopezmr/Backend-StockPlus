package com.tonilopezmr.stockplus.item.model

import com.tonilopezmr.stockplus.categories.model.Category

data class StockItemRequest(
    val name: String,
    val price: Double,
    val quantity: Int,
    val photo: List<String>?,
    val category: String,
    val referenceUrl: String,
    val datasheet: String?
)

fun StockItemRequest.toDomain(): StockItem = StockItem(
    id = "",
    name = name,
    price = price,
    quantity = quantity,
    photo = photo?.get(0) ?: "",
    category = Category(category, ""),
    referenceUrl = referenceUrl,
    datasheet = datasheet ?: ""
)