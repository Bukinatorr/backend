package xyz.bukinator.api.house

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import xyz.bukinator.api.house.dto.ListHouseRequest
import xyz.bukinator.api.house.dto.ListHouseResponse
import xyz.bukinator.house.service.HouseService

@RequestMapping("/api/house")
class HouseController(
    private val houseService: HouseService,
) {
    @GetMapping("/")
    fun list(@RequestBody body: ListHouseRequest): ListHouseResponse {
        val result = houseService.list(
            criteria = body.querySpecification.toQueryCriteria(),
            page = body.page.page,
            size = body.page.size
        )

        return ListHouseResponse(
            houses = result
        )
    }
}
