package com.tonilopezmr.stockplus.projects.model

import arrow.core.Either
import arrow.data.NonEmptyList
import arrow.instances.list.foldable.foldLeft
import arrow.instances.list.foldable.nonEmpty
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.usecase.GetItem

data class ProjectRequest(
    val name: String,
    val items: NonEmptyList<ItemQuantityRequest>
)

data class ItemQuantityRequest(
    val itemId: String,
    val quantity: Int
)

fun ItemQuantityRequest.toDomain(getItem: GetItem): Either<ItemError, ItemQuantity> =
    getItem(itemId).map { item ->
      ItemQuantity(item, quantity)
    }

fun ProjectRequest.toDomain(getItem: GetItem): Either<ProjectError, Project> =
    items.map { it.toDomain(getItem) }
        .foldLeft(
            
        )

        .fold(items) { acc, item ->
          if (acc.isNotEmpty() && acc)
        }
Project(
"",
name,
items.map { it.toDomain(getItem) },
0.0
)