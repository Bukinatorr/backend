package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty
import xyz.bukinator.client.domain.external.*

internal data class ItemDetailResponse(
    @JsonProperty("item")
    val item: ItemDetail,
) {
    data class ItemDetail (
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
}
