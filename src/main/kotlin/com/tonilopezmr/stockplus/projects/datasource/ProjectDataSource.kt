package com.tonilopezmr.stockplus.projects.datasource

import arrow.core.Try
import com.tonilopezmr.stockplus.base.datatypes.TryLogger
import com.tonilopezmr.stockplus.base.pagination.Pagination
import com.tonilopezmr.stockplus.base.pagination.request
import com.tonilopezmr.stockplus.projects.model.Projects
import org.springframework.stereotype.Component

@Component
class ProjectDataSource(
    private val projectDao: ProjectDao
) {

  fun all(pagination: Pagination): Try<Projects> = TryLogger(this::class) {
    projectDao.findAll(pagination.request())
        .toList()
        .map(ProjectEntity::toDomain)
  }

}
