package com.tonilopezmr.stockplus.categories.datasource

import com.tonilopezmr.stockplus.categories.Category
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Category")
data class CategoryEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: String,
    val name: String
)

fun CategoryEntity.toDomain(): Category = Category(
    id,
    name
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    id,
    name
)