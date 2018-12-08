package com.tonilopezmr.stockplus.projects.datasource

import arrow.core.Option
import arrow.core.Try
import arrow.core.toOption
import com.tonilopezmr.stockplus.base.datatypes.TryLogger
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.base.pagination.request
import com.tonilopezmr.stockplus.projects.model.Project
import com.tonilopezmr.stockplus.projects.model.Projects
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProjectDataSource(
    private val projectDao: ProjectDao
) {

  fun all(pagination: Pagination): Try<Projects> = TryLogger(this::class) {
    projectDao.findAll(pagination.request())
        .toList()
        .map(ProjectEntity::toDomain)
  }

  fun getById(id: String): Try<Option<Project>> = TryLogger(this::class) {
    projectDao.findById(UUID.fromString(id))
        .orElse(null)
        .toOption()
        .map(ProjectEntity::toDomain)
  }

  fun create(project: Project): Try<Project> = TryLogger(this::class) {
    projectDao.save(project.toEntity()).toDomain()
  }

  fun delete(project: Project): Try<Project> = TryLogger(this::class) {
    projectDao.delete(project.toEntity())
    project
  }
}
