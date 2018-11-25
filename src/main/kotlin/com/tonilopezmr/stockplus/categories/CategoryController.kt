package com.tonilopezmr.stockplus.categories

import com.tonilopezmr.stockplus.base.created
import com.tonilopezmr.stockplus.base.pagination.with
import com.tonilopezmr.stockplus.base.success
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.categories.model.CategoryError
import com.tonilopezmr.stockplus.categories.model.CategoryRequest
import com.tonilopezmr.stockplus.categories.model.toDomain
import com.tonilopezmr.stockplus.categories.usecase.CreateCategory
import com.tonilopezmr.stockplus.categories.usecase.DeleteCategory
import com.tonilopezmr.stockplus.categories.usecase.GetCategories
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/categories")
@Api(value = "Categories", description = "Categories resources")
class CategoryController(
    private val getCategories: GetCategories,
    private val createCategory: CreateCategory,
    private val deleteCategory: DeleteCategory
) {

  @GetMapping()
  @ApiOperation(value = "Get all items")
  @ApiResponses(
      ApiResponse(code = 200, response = Category::class, responseContainer = "List", message = "Items")
  )
  fun getAll(
      @RequestParam("page", required = false, defaultValue = "0") page: Int,
      @RequestParam("page_size", required = false, defaultValue = "0") pageSize: Int
  ): ResponseEntity<List<Category>> = getCategories(page with pageSize).fold(::error, ::success)

  @PostMapping("/")
  @ApiOperation(value = "Create a new Category")
  @ApiResponses(
      ApiResponse(code = 201, response = Category::class, message = "Category created"),
      ApiResponse(code = 400, message = "Validation error message")
  )
  fun create(
      @RequestBody categoryRequest: CategoryRequest
  ): ResponseEntity<Category> = createCategory(categoryRequest.toDomain()).fold(::error, ::created)

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete an existing Category")
  @ApiResponses(
      ApiResponse(code = 200, message = "Category removed"),
      ApiResponse(code = 400, message = "Category has items associated"),
      ApiResponse(code = 404, message = "Category was not found")
  )
  fun delete(@PathVariable id: String): ResponseEntity<Category> = deleteCategory(id).fold(::error, ::success)

  private fun <A> error(categoryError: CategoryError): ResponseEntity<A> = when (categoryError) {
    CategoryError.StorageError -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    CategoryError.NotFound -> ResponseEntity.notFound()
    CategoryError.EmptyName -> ResponseEntity.badRequest()
    CategoryError.CategoryHasItems -> ResponseEntity.badRequest()
  }.build()
}