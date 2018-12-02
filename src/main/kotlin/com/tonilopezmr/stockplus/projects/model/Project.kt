package com.tonilopezmr.stockplus.projects.model

import com.tonilopezmr.stockplus.item.model.StockItem

typealias Projects = List<Project>

data class Project(
    val id: String,
    val name: String,
    val items: List<ItemQuantity>,
    val totalPrice: Double
)

data class ItemQuantity(
    val item: StockItem,
    val quantity: Int
)