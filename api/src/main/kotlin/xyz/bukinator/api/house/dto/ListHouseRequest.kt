package xyz.bukinator.api.house.dto

import xyz.bukinator.house.model.embeddable.LatLng
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
        val minLat: Double?,
        val maxLat: Double?,
        val minLng: Double?,
        val maxLng: Double?,
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
                approveDateAfter = approveDateAfter?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) },
                minFloor = minFloor,
                screenLocation = if (minLat != null && maxLat != null && minLng != null && maxLng != null) {
                    HouseQueryCriteria.ScreenLocation(
                        northWest = LatLng(
                            lat = maxLat,
                            lng = minLng
                        ),
                        southEast = LatLng(
                            lat = minLat,
                            lng = maxLng
                        )
                    )
                } else {
                    null
                },
                deposit = if (minDeposit != null && maxDeposit != null) {
                    HouseQueryCriteria.Deposit(
                        min = minDeposit,
                        max = maxDeposit
                    )
                } else {
                    null
                },
                rent = if (minRent != null && maxRent != null) {
                    HouseQueryCriteria.Rent(
                        min = minRent,
                        max = maxRent
                    )
                } else {
                    null
                },
                parkingCount = parkingCount
            )
        }
    }
}
