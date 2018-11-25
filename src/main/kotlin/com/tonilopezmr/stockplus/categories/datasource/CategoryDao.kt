package com.tonilopezmr.stockplus.categories.datasource

import org.springframework.data.repository.PagingAndSortingRepository
import java.util.UUID

interface CategoryDao: PagingAndSortingRepository<CategoryEntity, UUID>