package xyz.bukinator.batch.zigbang.oneroom

import com.fasterxml.jackson.annotation.JsonProperty
import xyz.bukinator.batch.bukinator.ConvertibleToHouse
import xyz.bukinator.batch.bukinator.House

data class OneRoomItemIdResponse (
    @JsonProperty("items")
    val items: List<Item>,
) {
    data class Item(
        @JsonProperty("lat")
        val lat: Double,
        @JsonProperty("lng")
        val lng: Double,
        @JsonProperty("itemId:")
        val itemId: Long,
        @JsonProperty("itemBmType")
        val itemBmType: String,
    )
}

data class OneRoomItemListResponse (
    @JsonProperty("items") val items: List<OneRoomItemList>
)

data class OneRoomItemList (
    @JsonProperty("item_id") val itemId: Int,
    @JsonProperty("section_type") val sectionType: String?,
    @JsonProperty("images_thumbnail") val imagesThumbnail: String,
    @JsonProperty("sales_type") val salesType: String,
    @JsonProperty("sales_title") val salesTitle: String,
    @JsonProperty("deposit") val deposit: Int,
    @JsonProperty("rent") val rent: Int,
    @JsonProperty("size_m2") val sizeM2: Double,
    @JsonProperty("공급면적") val supplyArea: Area,
    @JsonProperty("전용면적") val exclusiveArea: Area,
    @JsonProperty("계약면적") val contractArea: String?,
    @JsonProperty("room_type_title") val roomTypeTitle: String?,
    @JsonProperty("floor") val floor: String,
    @JsonProperty("floor_string") val floorString: String,
    @JsonProperty("building_floor") val buildingFloor: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("is_first_movein") val isFirstMovein: Boolean?,
    @JsonProperty("room_type") val roomType: String,
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("tags") val tags: List<String>,
    @JsonProperty("service_type") val serviceType: String,
    @JsonProperty("random_location") val randomLocation: Location,
    @JsonProperty("manage_cost") val manageCost: String,
    @JsonProperty("reg_date") val regDate: String,
    @JsonProperty("is_new") val isNew: Boolean,
    @JsonProperty("addressOrigin") val addressOrigin: AddressOrigin,
    @JsonProperty("action") val action: Action,
    @JsonProperty("contract") val contract: String,
    @JsonProperty("address") val address: String,
    @JsonProperty("is_zzim") val isZzim: Boolean,
    @JsonProperty("address1") val address1: String,
    @JsonProperty("address2") val address2: String?,
    @JsonProperty("address3") val address3: String?,
    @JsonProperty("item_bm_type") val itemBmType: String
) : ConvertibleToHouse  {
    data class Area(
        @JsonProperty("m2") val m2: Double,
        @JsonProperty("p") val p: String
    )

    data class Location(
        @JsonProperty("lat") val lat: Double,
        @JsonProperty("lng") val lng: Double
    )

    data class AddressOrigin(
        @JsonProperty("local1") val local1: String,
        @JsonProperty("local2") val local2: String,
        @JsonProperty("local3") val local3: String,
        @JsonProperty("local4") val local4: String?,
        @JsonProperty("fullText") val fullText: String,
        @JsonProperty("localText") val localText: String
    )

    data class Action(
        @JsonProperty("isRead") val isRead: Boolean,
        @JsonProperty("readAt") val readAt: String?,
        @JsonProperty("isInquired") val isInquired: Boolean,
        @JsonProperty("inquiredAt") val inquiredAt: String?,
        @JsonProperty("isRewarded") val isRewarded: Boolean,
        @JsonProperty("rewardedAt") val rewardedAt: String?,
        @JsonProperty("isReported") val isReported: Boolean,
        @JsonProperty("reportedAt") val reportedAt: String?,
        @JsonProperty("isChecked") val isChecked: Boolean,
        @JsonProperty("checkedAt") val checkedAt: String?,
        @JsonProperty("isZzim") val isZzim: Boolean
    )

    override fun toHouse(): House {
        return House(
            originId = itemId.toString(),
            // 나머지 미구현
        )
    }
}

