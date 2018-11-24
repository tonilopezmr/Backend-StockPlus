package com.tonilopezmr.stockplus.item.model

sealed class ItemError {
  object CategoryNotExists: ItemError()
  object StorageError : ItemError()
  object NotFound : ItemError()
}