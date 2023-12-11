package xyz.bukinator.api.house.dto

import xyz.bukinator.house.service.dto.HouseQueryCriteria
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ListHouseRequest(
    val page: Page,
    val querySpecification: QuerySpecification,
) {
    data class Page(
        val size: Int,
        val page: Int,
    )
    data class QuerySpecification(
        val salesType: String?,
        val roomType: String?,
        val houseType: String?,
        val approveDateAfter: String?,
        val minFloor: Int?,
        val screenLocation: String?,
        val maxDeposit: Int?,
        val minDeposit: Int?,
        val maxRent: Int?,
        val minRent: Int?,
        val parkingCount: Int?,
    ) {
        fun toQueryCriteria(): HouseQueryCriteria {
            return HouseQueryCriteria(
                salesType = salesType,
                roomType = roomType,
                houseType = houseType,
                approveDateAfter = LocalDate.parse(approveDateAfter, DateTimeFormatter.ISO_DATE),
                minFloor = minFloor,
                screenLocation = screenLocation,
                deposit = HouseQueryCriteria.Deposit(
                    min = minDeposit,
                    max = maxDeposit
                ),
                rent = HouseQueryCriteria.Rent(
                    min = minRent,
                    max = maxRent
                ),
                parkingCount = parkingCount
            )
        }
    }
}
