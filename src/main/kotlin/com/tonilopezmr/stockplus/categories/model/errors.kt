package com.tonilopezmr.stockplus.categories.model

sealed class CategoryError {
  object StorageError: CategoryError()
  object NotFound: CategoryError()
  object EmptyName: CategoryError()
  object CategoryHasItems: CategoryError()
}