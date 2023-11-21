package xyz.bukinator.api.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.bukinator.api.application.HouseService
import xyz.bukinator.api.application.dto.response.HouseResponse
import xyz.bukinator.api.application.dto.response.HousesResponse

@RequestMapping("/api/houses")
@RestController
class HouseRestController(
    private val houseService: HouseService
) {
    @GetMapping
    fun findAll(): ResponseEntity<HousesResponse> {
        val responses = houseService.findAll()
        return ResponseEntity.ok(responses)
    }

    @GetMapping("/{houseId}")
    fun findById(
        @PathVariable houseId: Long
    ): ResponseEntity<HouseResponse> {
        val response = houseService.findById(houseId)
        return ResponseEntity.ok(response)
    }
}