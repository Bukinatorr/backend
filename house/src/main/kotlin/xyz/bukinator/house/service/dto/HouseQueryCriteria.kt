package xyz.bukinator.house.service.dto

import xyz.bukinator.house.model.embeddable.Location
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
    val area: RoomArea? = null,
    val isRentAndDepositConvertible: Boolean? = null,
) {
    data class ScreenLocation(
        val northWest: Location,
        val southEast: Location,
    )

    data class Deposit(
        val min: Int,
        val max: Int,
    )

    data class Rent(
        val min: Int,
        val max: Int,
    )

    data class RoomArea(
        val min: Double,
        val max: Double,
    )
}
