package com.tonilopezmr.stockplus.categories.datasource

import com.tonilopezmr.stockplus.categories.model.Category
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Category")
data class CategoryEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID,
    val name: String
)

fun CategoryEntity.toDomain(): Category = Category(
    id.toString(),
    name
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    if (id.isEmpty()) UUID.randomUUID() else UUID.fromString(id),
    name
)