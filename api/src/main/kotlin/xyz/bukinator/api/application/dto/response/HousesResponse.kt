package xyz.bukinator.api.application.dto.response

import xyz.bukinator.api.domain.house.House

data class HousesResponse(
    val data: List<HouseSummaryResponse>
) {
    companion object
}

data class HouseSummaryResponse(
    val id: Long,
    val salesType: String,
    val thumbnail: String,
    val priceDeposit: Int,
    val priceRent: Int,
    val title: String,
    val addressJibun: String
) {
    constructor(house: House) : this(
        house.id,
        house.salesType,
        house.thumbnail,
        house.price.priceDeposit,
        house.price.priceRent,
        house.title,
        house.address.addressJibun
    )
}