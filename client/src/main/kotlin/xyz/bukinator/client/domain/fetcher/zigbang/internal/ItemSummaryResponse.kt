package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty

internal data class ItemSummaryResponse(
    @JsonProperty("items")
    val items: List<ItemSummary>,
) {
    internal data class ItemSummary(
        @JsonProperty("item_id")
        val itemId: Long?,
        @JsonProperty("section_type")
        val sectionType: String?,
        @JsonProperty("images_thumbnail")
        val imagesThumbnail: String?,
        @JsonProperty("sales_type")
        val salesType: String?,
        @JsonProperty("sales_title")
        val salesTitle: String?,
        @JsonProperty("deposit")
        val deposit: Int?,
        @JsonProperty("rent")
        val rent: Int?,
        @JsonProperty("size_m2")
        val sizeM2: Double?,
        @JsonProperty("공급면적")
        val areaSupply: AreaSupply?,
        @JsonProperty("계약면적")
        val areaContract: AreaContract?,
        @JsonProperty("전용면적")
        val areaIndividual: AreaIndividual?,
        @JsonProperty("room_type_title")
        val roomTypeTitle: String?,
        @JsonProperty("floor")
        val floor: String?,
        @JsonProperty("floor_string")
        val floorString: String?,
        @JsonProperty("building_floor")
        val buildingFloor: String?,
        @JsonProperty("title")
        val title: String?,
        @JsonProperty("is_first_movein")
        val isFirstMovein: Any?,
        @JsonProperty("room_type")
        val roomType: String?,
        @JsonProperty("status")
        val status: Boolean?,
        @JsonProperty("tags")
        val tags: List<Any>?,
        @JsonProperty("service_type")
        val serviceType: String?,
        @JsonProperty("random_location")
        val randomLocation: RandomLocation?,
        @JsonProperty("manage_cost")
        val manageCost: String?,
        @JsonProperty("reg_date")
        val regDate: String?,
        @JsonProperty("is_new")
        val isNew: Boolean?,
        @JsonProperty("addressOrigin")
        val addressOrigin: AddressOrigin?,
        @JsonProperty("action")
        val action: Action?,
        @JsonProperty("contract")
        val contract: String?,
        @JsonProperty("address")
        val address: String?,
        @JsonProperty("is_zzim")
        val isZzim: Boolean?,
        @JsonProperty("address1")
        val address1: String?,
        @JsonProperty("address2")
        val address2: String?,
        @JsonProperty("address3")
        val address3: String?,
        @JsonProperty("item_bm_type")
        val itemBmType: String,
    ) {
        data class AreaSupply(
            @JsonProperty("m2")
            val m2: Double?,
            @JsonProperty("p")
            val p: String?,
        )

        data class AreaContract(
            @JsonProperty("m2")
            val m2: Double?,
            @JsonProperty("p")
            val p: String?,
        )

        data class AreaIndividual(
            @JsonProperty("m2")
            val m2: Double?,
            @JsonProperty("p")
            val p: String?,
        )

        data class RandomLocation(
            @JsonProperty("lat")
            val lat: Double?,
            @JsonProperty("lng")
            val lng: Double?,
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

        data class Action(
            @JsonProperty("isRead")
            val isRead: Boolean?,
            @JsonProperty("readAt")
            val readAt: Any?,
            @JsonProperty("isInquired")
            val isInquired: Boolean?,
            @JsonProperty("inquiredAt")
            val inquiredAt: Any?,
            @JsonProperty("isRewarded")
            val isRewarded: Boolean?,
            @JsonProperty("rewardedAt")
            val rewardedAt: Any?,
            @JsonProperty("isReported")
            val isReported: Boolean?,
            @JsonProperty("reportedAt")
            val reportedAt: Any?,
            @JsonProperty("isChecked")
            val isChecked: Boolean?,
            @JsonProperty("checkedAt")
            val checkedAt: Any?,
            @JsonProperty("isZzim")
            val isZzim: Boolean?,
        )
    }
}
