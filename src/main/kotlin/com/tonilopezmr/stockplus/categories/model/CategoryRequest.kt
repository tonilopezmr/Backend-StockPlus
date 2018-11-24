package com.tonilopezmr.stockplus.categories.model

data class CategoryRequest(
    val name: String
)

fun CategoryRequest.toDomain(): Category = Category(
    id = "",
    name = name
)