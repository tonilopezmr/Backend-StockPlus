package com.tonilopezmr.stockplus.projects.datasource

import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID

interface ProjectDao : PagingAndSortingRepository<ProjectEntity, UUID>