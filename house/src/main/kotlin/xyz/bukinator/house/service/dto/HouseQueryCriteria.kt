package xyz.bukinator.house.service.dto

import xyz.bukinator.house.model.embeddable.LatLng
import java.time.LocalDate

data class HouseQueryCriteria(
    val salesType: String? = null,
    val roomType: String? = null,
    val houseType: String? = null,
    val approveDateAfter: LocalDate? = null,
    val minFloor: Int? = null,
    val screenLocation: ScreenLocation? = null,
    val deposit: Deposit? = null,
    val rent: Rent? = null,
    val parkingCount: Int? = null,
) {
    data class ScreenLocation(
        val northWest: LatLng,
        val southEast: LatLng,
    )

    data class Deposit(
        val min: Int,
        val max: Int,
    )

    data class Rent(
        val min: Int,
        val max: Int,
    )
}