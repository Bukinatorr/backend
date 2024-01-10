package xyz.bukinator.api.house

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.bukinator.api.house.dto.ListHouseRequest
import xyz.bukinator.api.house.dto.ListHouseResponse
import xyz.bukinator.house.service.HouseQueryService

@RestController
@RequestMapping("/api/house")
class HouseController(
    private val houseQueryService: HouseQueryService,
) {
    @GetMapping("/")
    fun list(@RequestBody body: ListHouseRequest): ListHouseResponse {
        val result = houseQueryService.list(
            criteria = body.querySpecification.toQueryCriteria(),
            page = body.page.page,
            size = body.page.size
        )

        return ListHouseResponse(
            houses = result
        )
    }
}
