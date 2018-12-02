package com.tonilopezmr.stockplus.item.model

typealias StockItemResponses = List<StockItemResponse>

data class StockItemResponse(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val photo: String,
    val category: String,
    val referenceUrl: String,
    val datasheet: String
)

fun StockItem.toResponse(): StockItemResponse = StockItemResponse(
    id,
    name,
    price,
    quantity,
    photo,
    category.id,
    referenceUrl,
    datasheet
)