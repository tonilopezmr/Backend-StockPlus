package com.tonilopezmr.stockplus.projects

import com.tonilopezmr.stockplus.base.pagination.with
import com.tonilopezmr.stockplus.base.success
import com.tonilopezmr.stockplus.categories.model.Category
import com.tonilopezmr.stockplus.item.model.StockItemResponse
import com.tonilopezmr.stockplus.projects.model.Project
import com.tonilopezmr.stockplus.projects.model.ProjectError
import com.tonilopezmr.stockplus.projects.model.ProjectReponse
import com.tonilopezmr.stockplus.projects.model.Projects
import com.tonilopezmr.stockplus.projects.model.ProjectsResponse
import com.tonilopezmr.stockplus.projects.model.toResponse
import com.tonilopezmr.stockplus.projects.usecase.GetProject
import com.tonilopezmr.stockplus.projects.usecase.GetProjects
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/projects")
@Api(value = "Projects", description = "Projects resources")
class ProjectController(
  private val getProjects: GetProjects,
  private val getProject: GetProject
) {

  @GetMapping()
  @ApiOperation(value = "Get all projects")
  @ApiResponses(
      ApiResponse(code = 200, response = ProjectReponse::class, responseContainer = "List", message = "Items")
  )
  fun getAll(
      @RequestParam("page", required = false, defaultValue = "0") page: Int,
      @RequestParam("page_size", required = false, defaultValue = "0") pageSize: Int
  ): ResponseEntity<ProjectsResponse> = getProjects(page with pageSize).fold(::error, ::success)

  @GetMapping("/{id}")
  @ApiOperation(value = "Get a project by id")
  @ApiResponses(
      ApiResponse(code = 200, response = ProjectReponse::class, responseContainer = "List", message = "Category")
  )
  fun getById(@PathVariable id: String): ResponseEntity<ProjectReponse> = getProject(id).fold(::error, ::success)

  private fun success(projects: Projects): ResponseEntity<ProjectsResponse> =
      success(projects.map(Project::toResponse))

  private fun success(project: Project): ResponseEntity<ProjectReponse> =
      success(project.toResponse())

  private fun <A> error(projectError: ProjectError): ResponseEntity<A> = when (projectError) {
    ProjectError.StorageError -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    ProjectError.NotFound -> ResponseEntity.notFound()
  }.build()

}
