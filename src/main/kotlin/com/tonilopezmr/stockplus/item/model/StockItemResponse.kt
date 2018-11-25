package com.tonilopezmr.stockplus.item.model

data class StockItemResponse(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: String
)

fun StockItem.toResponse(): StockItemResponse = StockItemResponse(
    id = id,
    name = name,
    price = price,
    quantity = quantity,
    category = category.id
)