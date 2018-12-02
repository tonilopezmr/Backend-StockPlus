package com.tonilopezmr.stockplus.projects.model

sealed class ProjectError {
  object StorageError: ProjectError()
  object NotFound: ProjectError()
}