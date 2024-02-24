package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty
import xyz.bukinator.client.domain.external.ExternalDataDetail
import xyz.bukinator.client.domain.external.HouseStatus
import xyz.bukinator.client.domain.external.RoomDirection
import xyz.bukinator.client.domain.external.SalesType

internal data class ItemDetailResponse(
    @JsonProperty("item")
    val item: Item,
) : ExternalDataDetail {
    data class Item(
        @JsonProperty("itemId")
        val itemId: Long?,
        @JsonProperty("salesType")
        val salesType: String?,
        @JsonProperty("serviceType")
        val serviceType: String?,
        @JsonProperty("images")
        val images: List<String>?,
        @JsonProperty("imageThumbnail")
        val imageThumbnail: String?,
        @JsonProperty("price")
        val price: Price?,
        @JsonProperty("area")
        val area: Area?,
        @JsonProperty("roomType")
        val roomType: String?,
        @JsonProperty("title")
        val title: String?,
        @JsonProperty("description")
        val description: String?,
        @JsonProperty("status")
        val status: String?,
        @JsonProperty("randomLocation")
        val randomLocation: RandomLocation?,
        @JsonProperty("parkingAvailableText")
        val parkingAvailableText: String?,
        @JsonProperty("parkingCountText")
        val parkingCountText: String?,
        @JsonProperty("elevator")
        val elevator: Boolean?,
        @JsonProperty("roomDirection")
        val roomDirection: String?,
        @JsonProperty("directionCriterion")
        val directionCriterion: String?,
        @JsonProperty("moveinDate")
        val moveinDate: String?,
        @JsonProperty("pnu")
        val pnu: String?,
        @JsonProperty("floor")
        val floor: Floor?,
        @JsonProperty("buildingId")
        val buildingId: Long?,
        @JsonProperty("options")
        val options: List<String>?,
        @JsonProperty("manageCost")
        val manageCost: ManageCost?,
        @JsonProperty("isPremium")
        val isPremium: Boolean?,
        @JsonProperty("isHomepage")
        val isHomepage: Boolean?,
        @JsonProperty("hasUserPenalty")
        val hasUserPenalty: Boolean?,
        @JsonProperty("roomGubunCode")
        val roomGubunCode: String?,
        @JsonProperty("viewCount")
        val viewCount: Int?,
        @JsonProperty("updatedAt")
        val updatedAt: String?,
        @JsonProperty("approveDate")
        val approveDate: String?,
        @JsonProperty("bathroomCount")
        val bathroomCount: String?,
        @JsonProperty("residenceType")
        val residenceType: String?,
        @JsonProperty("addressOrigin")
        val addressOrigin: AddressOrigin?,
        @JsonProperty("jibunAddress")
        val jibunAddress: String?,
        @JsonProperty("자동종료대상")
        val isAutoCloseTarget: Boolean?,
        @JsonProperty("상태확인At")
        val stateCheckedAt: String?,
        @JsonProperty("itemBmType")
        val itemBmType: String?,
        @JsonProperty("checkedAt")
        val checkedAt: String?,
        @JsonProperty("photoTakenAt")
        val photoTakenAt: String?,
        @JsonProperty("petAllowed")
        val petAllowed: String?,
    ) {
        data class Price(
            @JsonProperty("deposit")
            val deposit: Int?,
            @JsonProperty("rent")
            val rent: Int?,
        )
        data class Area(
            @JsonProperty("전용면적M2")
            val individualAreaM2: Double?,
            @JsonProperty("계약면적M2")
            val contractAreaM2: Double?,
            @JsonProperty("공급면적M2")
            val supplyAreaM2: Double?,
        )
        data class RandomLocation(
            @JsonProperty("lat")
            val lat: Double?,
            @JsonProperty("lng")
            val lng: Double?,
        )
        data class Floor(
            @JsonProperty("allFloors")
            val allFloors: String?,
            @JsonProperty("floor")
            val floor: String?,
        )
        data class ManageCost(
            @JsonProperty("amount")
            val amount: Double?,
            @JsonProperty("includes")
            val includes: List<String>?,
            @JsonProperty("notIncludes")
            val notIncludes: List<String>?,
            @JsonProperty("type")
            val type: String?,
        )
        data class AddressOrigin(
            @JsonProperty("local1")
            val local1: String?,
            @JsonProperty("local2")
            val local2: String?,
            @JsonProperty("local3")
            val local3: String?,
            @JsonProperty("local4")
            val local4: String?,
            @JsonProperty("fullText")
            val fullText: String?,
            @JsonProperty("localText")
            val localText: String?,
        )
    }

    override fun getItemId(): String {
        return item.itemId.toString()
    }

    override fun getThumbnail(): String? {
        return item.imageThumbnail
    }

    override fun getTitle(): String? {
        return item.title
    }

    override fun getStatus(): HouseStatus? {
        return when (item.status) {
            "open" -> HouseStatus.OPEN
            "close" -> HouseStatus.CLOSE
            else -> null
        }
    }

    override fun getHouseName(): String? {
        return null
    }

    override fun getImages(): List<String>? {
        return item.images
    }

    override fun getDescription(): String? {
        return item.description
    }

    override fun getMoveinDate(): String? {
        return item.moveinDate
    }

    override fun getApproveDate(): String? {
        return item.approveDate
    }

    override fun getPnu(): String? {
        return item.pnu
    }

    override fun getOptions(): List<String>? {
        return item.options
    }

    override fun getSalesType(): SalesType? {
        return when (item.salesType) {
            "월세" -> SalesType.MONTHLY
            "전세" -> SalesType.JEONSE
            else -> null
        }
    }

    override fun getHouseType(): String? {
        return item.roomType
    }

    override fun getAreaContract(): Double? {
        return item.area?.contractAreaM2
    }

    override fun getAreaSupply(): Double? {
        return item.area?.supplyAreaM2
    }

    override fun getAreaIndividual(): Double? {
        return item.area?.individualAreaM2
    }

    override fun getFloorTotal(): Int? {
        return item.floor?.allFloors?.toIntOrNull()
    }

    override fun getFloorTarget(): Int? {
        return item.floor?.floor?.toIntOrNull()
    }

    override fun getRoomType(): String? {
        return item.roomType
    }

    override fun getRoomDirection(): RoomDirection? {
        return when (item.roomDirection) {
            "동", "E" -> RoomDirection.EAST
            "서", "W" -> RoomDirection.WEST
            "남", "S" -> RoomDirection.SOUTH
            "북", "N" -> RoomDirection.NORTH
            "남동", "동남", "SE", "ES" -> RoomDirection.SOUTH_EAST
            "남서", "서남", "SW", "WS" -> RoomDirection.SOUTH_WEST
            "북동", "동북", "NE", "EN" -> RoomDirection.NORTH_EAST
            "북서", "서북", "NW", "WN" -> RoomDirection.NORTH_WEST
            else -> null
        }
    }

    override fun getParkingCount(): Double? {
        return item.parkingCountText?.let { extractNumberFromString(it) }
    }

    override fun hasElevator(): Boolean? {
        return item.elevator
    }

    override fun getResidenceType(): String? {
        return item.residenceType
    }

    override fun getLocationLat(): Double? {
        return item.randomLocation?.lat
    }

    override fun getLocationLng(): Double? {
        return item.randomLocation?.lng
    }

    override fun getAddressLocal1(): String? {
        return item.addressOrigin?.local1
    }

    override fun getAddressLocal2(): String? {
        return item.addressOrigin?.local2
    }

    override fun getAddressLocal3(): String? {
        return item.addressOrigin?.local3
    }

    override fun getAddressLocal4(): String? {
        return item.addressOrigin?.local4
    }

    override fun getAddressJibun(): String? {
        return item.jibunAddress
    }

    override fun getPriceDeposit(): Int? {
        return item.price?.deposit
    }

    override fun getPriceRent(): Int? {
        return item.price?.rent
    }

    override fun getPriceManage(): Double? {
        return item.manageCost?.amount
    }

    override fun getPriceManageIncludes(): List<String>? {
        return item.manageCost?.includes
    }

    fun extractNumberFromString(text: String): Double? {
        val regex = Regex("[0-9]+\\.?[0-9]*") // 소수점을 포함하는 숫자에 매칭되는 정규 표현식
        val matchResult = regex.find(text)
        return matchResult?.value?.toDoubleOrNull()
    }
}
