package com.tonilopezmr.stockplus.projects.model

typealias ProjectsResponse = List<ProjectReponse>

data class ProjectReponse(
    val id: String,
    val name: String,
    val items: List<ItemQuantityResponse>,
    val totalPrice: Double
)

data class ItemQuantityResponse(
    val itemId: String,
    val quantity: Int
)

fun Project.toResponse(): ProjectReponse = ProjectReponse(
    id,
    name,
    items.map(::quantityReponse),
    totalPrice
)

private fun quantityReponse(itemQuantity: ItemQuantity): ItemQuantityResponse = ItemQuantityResponse(
    itemQuantity.item.id,
    itemQuantity.quantity
)