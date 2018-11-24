package com.tonilopezmr.stockplus.categories.datasource

import org.springframework.data.repository.PagingAndSortingRepository

interface CategoryDao: PagingAndSortingRepository<CategoryEntity, String>