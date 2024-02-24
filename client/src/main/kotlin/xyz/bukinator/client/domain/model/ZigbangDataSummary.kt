package xyz.bukinator.client.domain.model

import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.client.domain.external.HouseStatus
import xyz.bukinator.client.domain.external.SalesType
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ItemSummaryResponse

internal class ZigbangDataSummary(
    private val itemSummary: ItemSummaryResponse.ItemSummary,
) : ExternalDataSummary {
    override fun getItemId(): String {
        return itemSummary.itemId.toString()
    }

    override fun getThumbnail(): String? {
        return itemSummary.imagesThumbnail
    }

    override fun getTitle(): String? {
        return itemSummary.title
    }

    override fun getStatus(): HouseStatus? {
        return when (itemSummary.status) {
            true -> HouseStatus.OPEN
            false -> HouseStatus.CLOSE
            else -> null
        }
    }

    override fun getSalesType(): SalesType? {
        return when (itemSummary.salesType) {
            "월세" -> SalesType.MONTHLY
            "전세" -> SalesType.JEONSE
            else -> null
        }
    }

    override fun getHouseType(): String? {
        return itemSummary.roomTypeTitle
    }

    override fun getAreaContract(): Double? {
        return itemSummary.areaContract?.m2
    }

    override fun getAreaSupply(): Double? {
        return itemSummary.areaSupply?.m2
    }

    override fun getAreaIndividual(): Double? {
        return itemSummary.areaIndividual?.m2
    }

    override fun getFloorTotal(): Int? {
        return itemSummary.buildingFloor?.toIntOrNull()
    }

    override fun getFloorTarget(): Int? {
        return itemSummary.floor?.toIntOrNull()
    }

    override fun getLocationLat(): Double? {
        return itemSummary.randomLocation?.lat
    }

    override fun getLocationLng(): Double? {
        return itemSummary.randomLocation?.lng
    }

    override fun getAddressLocal1(): String? {
        return itemSummary.addressOrigin?.local1
    }

    override fun getAddressLocal2(): String? {
        return itemSummary.addressOrigin?.local2
    }

    override fun getAddressLocal3(): String? {
        return itemSummary.addressOrigin?.local3
    }

    override fun getAddressLocal4(): String? {
        return itemSummary.addressOrigin?.local4
    }

    override fun getPriceDeposit(): Int? {
        return itemSummary.deposit
    }

    override fun getPriceRent(): Int? {
        return itemSummary.rent
    }

    override fun getPriceManage(): Double? {
        return itemSummary.manageCost?.toDoubleOrNull()
    }
}
