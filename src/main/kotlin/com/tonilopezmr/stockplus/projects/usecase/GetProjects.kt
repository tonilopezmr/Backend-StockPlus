package com.tonilopezmr.stockplus.projects.usecase

import arrow.core.Either
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.projects.datasource.ProjectDataSource
import com.tonilopezmr.stockplus.projects.model.ProjectError
import com.tonilopezmr.stockplus.projects.model.Projects
import org.springframework.stereotype.Component

@Component
class GetProjects(
    private val projectDataSource: ProjectDataSource
) {
  operator fun invoke(pagination: Pagination): Either<ProjectError, Projects> =
      projectDataSource.all(pagination).toEither { ProjectError.StorageError }
}