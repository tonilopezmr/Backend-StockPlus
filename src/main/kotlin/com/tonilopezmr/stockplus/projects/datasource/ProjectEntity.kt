package com.tonilopezmr.stockplus.projects.datasource

import com.tonilopezmr.stockplus.item.datasource.StockItemEntity
import com.tonilopezmr.stockplus.item.datasource.toDomain
import com.tonilopezmr.stockplus.projects.model.ItemQuantity
import com.tonilopezmr.stockplus.projects.model.Project
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Projects")
data class ProjectEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID,
    val name: String,
    val items: List<ItemQuantityEntity>,
    val totalPrice: Double
)

@Entity
@Table(name = "ItemQuantity")
data class ItemQuantityEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID,
    val item: StockItemEntity,
    val quantity: Int
) {
  val price: Double
    get() = item.price * quantity
}

fun ProjectEntity.toDomain(): Project = Project(
    id.toString(),
    name,
    items.map(ItemQuantityEntity::toDomain),
    items.map { it.price }.sum()
)

fun ItemQuantityEntity.toDomain(): ItemQuantity = ItemQuantity(
    item.toDomain(),
    quantity
)