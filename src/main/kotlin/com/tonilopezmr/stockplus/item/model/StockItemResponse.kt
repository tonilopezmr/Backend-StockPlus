package com.tonilopezmr.stockplus.item.model

typealias StockItemResponses = List<StockItemResponse>

data class StockItemResponse(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val photo: String,
    val category: String
)

fun StockItem.toResponse(): StockItemResponse = StockItemResponse(
    id = id,
    name = name,
    price = price,
    quantity = quantity,
    photo = photo,
    category = category.id
)