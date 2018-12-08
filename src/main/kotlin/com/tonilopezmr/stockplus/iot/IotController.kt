package com.tonilopezmr.stockplus.iot

import com.tonilopezmr.stockplus.item.model.StockItemResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("/v1/iot")
@Api(value = "Internet Of Things", description = "Projects resources")
class IotController {

  val deviceList = mutableMapOf<String, Device>()

  @PostMapping("/new")
  @ApiOperation(value = "Add new device")
  @ApiResponses(
      ApiResponse(code = 200, response = String::class, message = "Items")
  )
  fun new(@RequestBody device: Device): ResponseEntity<String> {
    deviceList[device.mac] = device
    return ResponseEntity.ok("ok")
  }

  @PostMapping()
  @ApiOperation(value = "Change state")
  @ApiResponses(
      ApiResponse(code = 200, response = Boolean::class,  message = "Items")
  )
  fun doSomething(@RequestBody mac: String): ResponseEntity<Device> {
    val device = deviceList[mac]!!

    val restTemplate = RestTemplate()
    val state = restTemplate.getForObject("http://${device.ip}", Boolean::class.java)!!

    val newDevice = device.copy(state = state)
    deviceList[device.mac] = newDevice
    return ResponseEntity.ok(newDevice)
  }


  @GetMapping()
  @ApiOperation(value = "Get all devices")
  @ApiResponses(
      ApiResponse(code = 200, response = Device::class, responseContainer = "List", message = "Items")
  )
  fun getAll(): ResponseEntity<List<Device>> =
      ResponseEntity.ok(deviceList.values.toList().map {
        val isOnline= isDeviceAlive(it)
        it.copy(online = isOnline)
      })

  private fun isDeviceAlive(device: Device): Boolean {
    val restTemplate = RestTemplate()
    val state = restTemplate.getForEntity("http://${device.ip}/health", String::class.java)
    return state.statusCode == HttpStatus.OK
  }

}