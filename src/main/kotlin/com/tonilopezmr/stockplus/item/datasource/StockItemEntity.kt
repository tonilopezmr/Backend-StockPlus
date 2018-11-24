package com.tonilopezmr.stockplus.item.datasource

import com.tonilopezmr.stockplus.categories.datasource.CategoryEntity
import com.tonilopezmr.stockplus.categories.datasource.toDomain
import com.tonilopezmr.stockplus.categories.datasource.toEntity
import com.tonilopezmr.stockplus.item.model.StockItem
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "StockItem")
data class StockItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: CategoryEntity
)

fun StockItemEntity.toDomain(): StockItem = StockItem(
    id,
    name,
    price,
    quantity,
    category.toDomain()
)

fun StockItem.toEntity(): StockItemEntity = StockItemEntity(
    id,
    name,
    price,
    quantity,
    category.toEntity()
)