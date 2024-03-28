package xyz.bukinator.client.domain.model

import xyz.bukinator.client.domain.external.*
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ItemDetailResponse
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ItemSummaryResponse

internal class ZigbangDataDetail(
    private val itemDetail: ItemDetailResponse.ItemDetail
) : ExternalDataDetail {
    override fun getItemId(): String {
        return itemDetail.itemId.toString()
    }

    override fun getThumbnail(): String? {
        return itemDetail.imageThumbnail
    }

    override fun getTitle(): String? {
        return itemDetail.title
    }

    override fun getStatus(): HouseStatus {
        return when (itemDetail.status) {
            "open" -> HouseStatus.OPEN
            "close" -> HouseStatus.CLOSE
            else -> HouseStatus.UNKNOWN
        }
    }

    override fun getHouseName(): String {
        return ""
    }

    override fun getImages(): List<String>? {
        return itemDetail.images
    }

    override fun getDescription(): String? {
        return itemDetail.description
    }

    override fun getMoveinDate(): String? {
        return itemDetail.moveinDate
    }

    override fun getApproveDate(): String? {
        return ""
    }

    override fun getPnu(): String? {
        return itemDetail.pnu
    }

    override fun getOptions(): List<String>? {
        return itemDetail.options
    }

    override fun getSalesType(): SalesType {
        return when (itemDetail.salesType) {
            "월세" -> SalesType.MONTHLY
            "전세" -> SalesType.JEONSE
            else -> SalesType.UNKNOWN
        }
    }

    override fun getHouseType(): HouseType {
        return when (itemDetail.serviceType) {
            "오피스텔" -> HouseType.OFFICETEL
            "원룸" -> HouseType.ONEROOM
            else -> HouseType.UNKNOWN
        }
    }

    override fun getRoomDirection(): RoomDirection {
        return when (itemDetail.roomDirection) {
            "동", "E" -> RoomDirection.EAST
            "서", "W" -> RoomDirection.WEST
            "남", "S" -> RoomDirection.SOUTH
            "북", "N" -> RoomDirection.NORTH
            "남동", "동남", "SE", "ES" -> RoomDirection.SOUTH_EAST
            "남서", "서남", "SW", "WS" -> RoomDirection.SOUTH_WEST
            "북동", "동북", "NE", "EN" -> RoomDirection.NORTH_EAST
            "북서", "서북", "NW", "WN" -> RoomDirection.NORTH_WEST
            else -> RoomDirection.UNKNOWN
        }
    }

    override fun getParkingCount(): Double? {
        return itemDetail.parkingCountText?.let { extractNumberFromString(it) }
    }

    override fun hasElevator(): Boolean? {
        return itemDetail.elevator
    }

    override fun getResidenceType(): String? {
        return itemDetail.residenceType
    }

    override fun getUpdatedAt(): String {
        return itemDetail.updatedAt ?: ""
    }

    override fun getRoomType(): String? {
        return itemDetail.roomType
    }

    override fun getAreaContract(): Double? {
        return itemDetail.area?.contractAreaM2
    }

    override fun getAreaSupply(): Double? {
        return itemDetail.area?.supplyAreaM2
    }

    override fun getAreaIndividual(): Double? {
        return itemDetail.area?.individualAreaM2
    }

    override fun getFloorTotal(): Int? {
        return itemDetail.floor?.allFloors?.toIntOrNull()
    }

    override fun getFloorTarget(): Int? {
        return itemDetail.floor?.floor?.toIntOrNull()
    }

    override fun getLocationLat(): Double? {
        return itemDetail.randomLocation?.lat
    }

    override fun getLocationLng(): Double? {
        return itemDetail.randomLocation?.lng
    }

    override fun getAddressLocal1(): String? {
        return itemDetail.addressOrigin?.local1
    }

    override fun getAddressLocal2(): String? {
        return itemDetail.addressOrigin?.local2
    }

    override fun getAddressLocal3(): String? {
        return itemDetail.addressOrigin?.local3
    }

    override fun getAddressLocal4(): String? {
        return itemDetail.addressOrigin?.local4
    }

    override fun getAddressJibun(): String? {
        return itemDetail.jibunAddress
    }

    override fun getPriceDeposit(): Int? {
        return itemDetail.price?.deposit
    }

    override fun getPriceRent(): Int? {
        return itemDetail.price?.rent
    }

    override fun getPriceManage(): Double? {
        return itemDetail.manageCost?.amount
    }

    override fun getPriceManageIncludes(): List<String>? {
        return itemDetail.manageCost?.includes
    }

    private fun extractNumberFromString(text: String): Double? {
        val regex = Regex("[0-9]+\\.?[0-9]*") // 소수점을 포함하는 숫자에 매칭되는 정규 표현식
        val matchResult = regex.find(text)
        return matchResult?.value?.toDoubleOrNull()
    }
}
