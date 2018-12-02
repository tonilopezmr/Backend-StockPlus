package com.tonilopezmr.stockplus.projects.usecase

import arrow.core.Either
import arrow.core.flatMap
import com.tonilopezmr.stockplus.categories.model.CategoryError
import com.tonilopezmr.stockplus.projects.datasource.ProjectDataSource
import com.tonilopezmr.stockplus.projects.model.Project
import com.tonilopezmr.stockplus.projects.model.ProjectError
import org.springframework.stereotype.Component

@Component
class GetProject(
    private val dataSource: ProjectDataSource
) {
  operator fun invoke(id: String): Either<ProjectError, Project> =
      dataSource.getById(id)
          .toEither { ProjectError.StorageError }
          .flatMap { it.toEither { ProjectError.NotFound } }
}