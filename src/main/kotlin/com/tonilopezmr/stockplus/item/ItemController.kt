package com.tonilopezmr.stockplus.item

import com.tonilopezmr.stockplus.base.created
import com.tonilopezmr.stockplus.base.pagination.with
import com.tonilopezmr.stockplus.base.success
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.item.model.ItemError
import com.tonilopezmr.stockplus.item.model.StockItem
import com.tonilopezmr.stockplus.item.model.StockItemRequest
import com.tonilopezmr.stockplus.item.model.StockItemResponse
import com.tonilopezmr.stockplus.item.model.toDomain
import com.tonilopezmr.stockplus.item.model.toResponse
import com.tonilopezmr.stockplus.item.usecase.CreateItem
import com.tonilopezmr.stockplus.item.usecase.DeleteItem
import com.tonilopezmr.stockplus.item.usecase.GetItem
import com.tonilopezmr.stockplus.item.usecase.GetItems
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
@RequestMapping("/v1/items")
@Api(value = "Items", description = "Items resources")
class ItemController(
    private val getItems: GetItems,
    private val createItem: CreateItem,
    private val deleteItem: DeleteItem,
    private val getItem: GetItem
) {

  @GetMapping()
  @ApiOperation(value = "Get all items")
  @ApiResponses(
      ApiResponse(code = 200, response = StockItemResponse::class, responseContainer = "List", message = "Items")
  )
  fun getAll(
      @RequestParam("page", required = false, defaultValue = "0") page: Int,
      @RequestParam("page_size", required = false, defaultValue = "0") pageSize: Int
  ): ResponseEntity<List<StockItemResponse>> = getItems(page with pageSize).fold(::error, ::success)

  @PostMapping()
  @ApiOperation(value = "Create a new Item")
  @ApiResponses(
      ApiResponse(code = 201, response = StockItemResponse::class, message = "Item created"),
      ApiResponse(code = 400, message = "Validation error message"),
      ApiResponse(code = 404, message = "Category not exists")
  )
  fun create(
      @RequestBody stockItemRequest: StockItemRequest
  ): ResponseEntity<StockItemResponse> = createItem(stockItemRequest.toDomain()).fold(::error, ::created)

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete an existing Item")
  @ApiResponses(
      ApiResponse(code = 200, response = StockItemResponse::class, message = "Item removed"),
      ApiResponse(code = 404, message = "Item was not found")
  )
  fun delete(@PathVariable id: String): ResponseEntity<StockItemResponse> = deleteItem(id).fold(::error, ::success)

  @GetMapping("/{id}")
  @ApiOperation(value = "Get item by id")
  @ApiResponses(
      ApiResponse(code = 200, response = StockItemResponse::class, responseContainer = "List", message = "Item")
  )
  fun getById(@PathVariable id: String): ResponseEntity<StockItemResponse> = getItem(id).fold(::error, ::success)


  private fun success(items: List<StockItem>): ResponseEntity<List<StockItemResponse>> =
      success(items.map { it.toResponse() })

  private fun success(item: StockItem): ResponseEntity<StockItemResponse> =
      success(item.toResponse())

  private fun created(item: StockItem): ResponseEntity<StockItemResponse> =
      created(item.toResponse())

  private fun <A> error(itemError: ItemError): ResponseEntity<A> = when (itemError) {
    ItemError.NotFound -> ResponseEntity.notFound()
    ItemError.StorageError -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    ItemError.CategoryNotExists -> ResponseEntity.badRequest()
  }.build()
}