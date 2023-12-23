package xyz.bukinator.api.house

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.mock.web.MockServletContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import xyz.bukinator.api.BaseIntegrationTest
import xyz.bukinator.house.model.House
import xyz.bukinator.house.model.embeddable.Address
import xyz.bukinator.house.model.embeddable.Area
import xyz.bukinator.house.model.embeddable.Floor
import xyz.bukinator.house.model.embeddable.LatLng
import xyz.bukinator.house.model.embeddable.Origin
import xyz.bukinator.house.model.embeddable.Price
import xyz.bukinator.house.model.enumerate.HouseStatus
import xyz.bukinator.house.service.HouseService
import java.time.LocalDate
import java.util.*

class HouseControllerTest : BaseIntegrationTest() {

    @Autowired
    lateinit var houseService: HouseService

    @Test
    @DisplayName("컨트롤러가 context에 잘 등록되어야 한다")
    fun controller_register_test() {
        val servletContext = webApplicationContext.servletContext
        Assertions.assertNotNull(servletContext)
        Assertions.assertTrue(servletContext is MockServletContext)
        Assertions.assertNotNull(webApplicationContext.getBean("houseController"))
    }

    @Test
    @DisplayName("list api가 잘 동작해야 한다")
    fun list_test() {
        (1..10).map {
            houseService.create(
                House(
                    id = UUID.randomUUID(),
                    description = "test",
                    origin = Origin(
                        originSource = "ipsum",
                        originId = "donec",
                        originUpdatedAt = null
                    ),
                    salesType = if (it % 2 == 0) "월세" else "전세",
                    houseName = null,
                    houseType = "sed",
                    roomType = "ad",
                    roomDirection = "lacinia",
                    thumbnail = "no",
                    images = listOf(),
                    price = Price(
                        priceDeposit = 8629,
                        priceRent = 6182,
                        priceManage = 7403,
                        priceManageIncludes = listOf()
                    ),
                    area = Area(
                        areaContract = null,
                        areaSupply = 30.0,
                        areaIndividual = null
                    ),
                    title = "luctus",
                    status = HouseStatus.OPEN,
                    location = LatLng(lat = 4.5, lng = 6.7),
                    parkingCount = 4773,
                    elevator = false,
                    movinDate = LocalDate.now(),
                    approveDate = LocalDate.now(),
                    residenceType = "dicta",
                    pnu = "erat",
                    floor = Floor(floorTotal = 4912, floorTarget = 1809),
                    options = listOf(),
                    address = Address(
                        addressLocal1 = "netus",
                        addressLocal2 = "inimicus",
                        addressLocal3 = "mattis",
                        addressLocal4 = "epicuri",
                        addressJibun = "discere"
                    )
                )
            )
        }

        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/house/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "querySpecification": {
                            "salesType": "월세"
                        },
                        "page": {
                            "page": 0,
                            "size": 10
                        }
                    }
                    """.trimIndent()
                )
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.houses").isArray)
            .andReturn()
        println(result.response.contentAsString)
    }
}
