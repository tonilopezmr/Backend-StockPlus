package com.tonilopezmr.stockplus.projects.usecase

import arrow.core.Either
import arrow.core.Option
import arrow.core.flatMap
import com.tonilopezmr.stockplus.projects.datasource.ProjectDataSource
import com.tonilopezmr.stockplus.projects.datasource.ProjectEntity
import com.tonilopezmr.stockplus.projects.model.Project
import com.tonilopezmr.stockplus.projects.model.ProjectError
import org.springframework.stereotype.Component

@Component
class DeleteProject(
    private val dataSource: ProjectDataSource,
    private val getProject: GetProject
) {

  operator fun invoke(id: String): Either<ProjectError, Project> =
      getProject(id).flatMap { project ->
        dataSource.delete(project).toEither { ProjectError.StorageError }
      }
}