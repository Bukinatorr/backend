package xyz.bukinator.client.domain.model

import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.client.domain.external.HouseStatus
import xyz.bukinator.client.domain.external.HouseType
import xyz.bukinator.client.domain.external.SalesType
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ItemSummaryResponse

internal class ZigbangDataDetail(
    private val itemDetail: ItemSummaryResponse.ItemSummary,
) : ExternalDataSummary {
    override fun getItemId(): String {
        return itemDetail.itemId.toString()
    }

    override fun getThumbnail(): String? {
        return itemDetail.imagesThumbnail
    }

    override fun getTitle(): String? {
        return itemDetail.title
    }

    override fun getStatus(): HouseStatus {
        return when (itemDetail.status) {
            true -> HouseStatus.OPEN
            false -> HouseStatus.CLOSE
            else -> HouseStatus.UNKNOWN
        }
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

    override fun getRoomType(): String? {
        return itemDetail.roomType
    }

    override fun getAreaContract(): Double? {
        return itemDetail.areaContract?.m2
    }

    override fun getAreaSupply(): Double? {
        return itemDetail.areaSupply?.m2
    }

    override fun getAreaIndividual(): Double? {
        return itemDetail.areaIndividual?.m2
    }

    override fun getFloorTotal(): Int? {
        return itemDetail.buildingFloor?.toIntOrNull()
    }

    override fun getFloorTarget(): Int? {
        return itemDetail.floor?.toIntOrNull()
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

    override fun getPriceDeposit(): Int? {
        return itemDetail.deposit
    }

    override fun getPriceRent(): Int? {
        return itemDetail.rent
    }

    override fun getPriceManage(): Double? {
        return itemDetail.manageCost?.toDoubleOrNull()
    }
}
