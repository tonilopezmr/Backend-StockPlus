package com.tonilopezmr.stockplus.projects.usecase

import arrow.core.Either
import com.tonilopezmr.stockplus.projects.datasource.ProjectDataSource
import com.tonilopezmr.stockplus.projects.model.Project
import com.tonilopezmr.stockplus.projects.model.ProjectError
import org.springframework.stereotype.Component

@Component
class CreateProject(
    private val dataSource: ProjectDataSource
) {
  operator fun invoke(project: Project): Either<ProjectError, Project> =
      dataSource.create(project).toEither { ProjectError.StorageError }
}