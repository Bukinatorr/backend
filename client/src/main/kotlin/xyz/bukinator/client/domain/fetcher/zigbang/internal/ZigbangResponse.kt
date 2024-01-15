package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty
import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSource
import xyz.bukinator.client.domain.model.ExternalDataSummary

internal data class ZigbangOneroomItemIdResponse(
    @JsonProperty("items")
    val items: List<Item>,
) {
    data class Item(
        @JsonProperty("lat")
        val lat: Double,
        @JsonProperty("lng")
        val lng: Double ,
        @JsonProperty("itemId")
        val itemId: Long,
        @JsonProperty("itemBmType")
        val itemBmType: String,
    )
}

internal data class ZigbangOfficetelItemIdResponse(
    @JsonProperty("items")
    val items: List<Item>,
) {
    data class Item(
        @JsonProperty("buildingId")
        val buildingId: Long,
        @JsonProperty("itemId")
        val itemId: Long,
        @JsonProperty("itemBmType")
        val itemBmType: String,
    )
}

internal data class ZigbangOneroomItemListResponse(
    @JsonProperty("items")
    val items: List<OneroomItemList>,
) : ExternalDataSummary(source = ExternalDataSource.ZIGBANG_ONEROOM) {
    data class OneroomItemList(
        @JsonProperty("item_id")
        val itemId: Long,
        @JsonProperty("section_type")
        val sectionType: String?,
        @JsonProperty("images_thumbnail")
        val imagesThumbnail: String,
        @JsonProperty("sales_type")
        val salesType: String,
        @JsonProperty("sales_title")
        val salesTitle: String,
        @JsonProperty("deposit")
        val deposit: Int,
        @JsonProperty("rent")
        val rent: Int,
        @JsonProperty("size_m2")
        val sizeM2: Double,
        @JsonProperty("공급면적")
        val areaSupply: AreaSupply?,
        @JsonProperty("계약면적")
        val areaContract: AreaContract?,
        @JsonProperty("전용면적")
        val areaIndividual: AreaIndividual?,
        @JsonProperty("room_type_title")
        val roomTypeTitle: String?,
        @JsonProperty("floor")
        val floor: String,
        @JsonProperty("floor_string")
        val floorString: String,
        @JsonProperty("building_floor")
        val buildingFloor: String,
        @JsonProperty("title")
        val title: String,
        @JsonProperty("is_first_movein")
        val isFirstMovein: Any?,
        @JsonProperty("room_type")
        val roomType: String,
        @JsonProperty("status")
        val status: Boolean,
        @JsonProperty("tags")
        val tags: List<Any>,
        @JsonProperty("service_type")
        val serviceType: String,
        @JsonProperty("random_location")
        val randomLocation: RandomLocation,
        @JsonProperty("manage_cost")
        val manageCost: String,
        @JsonProperty("reg_date")
        val regDate: String,
        @JsonProperty("is_new")
        val isNew: Boolean,
        @JsonProperty("addressOrigin")
        val addressOrigin: AddressOrigin,
        @JsonProperty("action")
        val action: Action,
        @JsonProperty("contract")
        val contract: String,
        @JsonProperty("address")
        val address: String,
        @JsonProperty("is_zzim")
        val isZzim: Boolean,
        @JsonProperty("address1")
        val address1: String,
        @JsonProperty("address2")
        val address2: Any?,
        @JsonProperty("address3")
        val address3: Any?,
        @JsonProperty("item_bm_type")
        val itemBmType: String
    ) {
        data class AreaSupply(
            @JsonProperty("m2")
            val m2: Double,
            @JsonProperty("p")
            val p: String,
        )

        data class AreaContract(
            @JsonProperty("m2")
            val m2: Double,
            @JsonProperty("p")
            val p: String,
        )

        data class AreaIndividual(
            @JsonProperty("m2")
            val m2: Double,
            @JsonProperty("p")
            val p: String,
        )

        data class RandomLocation(
            @JsonProperty("lat")
            val lat: Double,
            @JsonProperty("lng")
            val lng: Double,
        )

        data class AddressOrigin(
            @JsonProperty("local1")
            val local1: String,
            @JsonProperty("local2")
            val local2: String,
            @JsonProperty("local3")
            val local3: String,
            @JsonProperty("local4")
            val local4: String,
            @JsonProperty("fullText")
            val fullText: String,
            @JsonProperty("localText")
            val localText: String,
        )

        data class Action(
            @JsonProperty("isRead")
            val isRead: Boolean,
            @JsonProperty("readAt")
            val readAt: Any?,
            @JsonProperty("isInquired")
            val isInquired: Boolean,
            @JsonProperty("inquiredAt")
            val inquiredAt: Any?,
            @JsonProperty("isRewarded")
            val isRewarded: Boolean,
            @JsonProperty("rewardedAt")
            val rewardedAt: Any?,
            @JsonProperty("isReported")
            val isReported: Boolean,
            @JsonProperty("reportedAt")
            val reportedAt: Any?,
            @JsonProperty("isChecked")
            val isChecked: Boolean,
            @JsonProperty("checkedAt")
            val checkedAt: Any?,
            @JsonProperty("isZzim")
            val isZzim: Boolean,
        )
    }
}

internal data class ZigbangOfficetelItemListResponse(
    @JsonProperty("items")
    val items: List<OneroomItemList>,
) : ExternalDataSummary(source = ExternalDataSource.ZIGBANG_OFFICETEL) {
    data class OneroomItemList(
        @JsonProperty("item_id")
        val itemId: Long,
        @JsonProperty("section_type")
        val sectionType: String?,
        @JsonProperty("images_thumbnail")
        val imagesThumbnail: String,
        @JsonProperty("sales_type")
        val salesType: String,
        @JsonProperty("sales_title")
        val salesTitle: String,
        @JsonProperty("deposit")
        val deposit: Int,
        @JsonProperty("rent")
        val rent: Int,
        @JsonProperty("size_m2")
        val sizeM2: Double,
        @JsonProperty("공급면적")
        val areaSupply: AreaSupply?,
        @JsonProperty("계약면적")
        val areaContract: AreaContract?,
        @JsonProperty("전용면적")
        val areaIndividual: AreaIndividual?,
        @JsonProperty("room_type_title")
        val roomTypeTitle: String?,
        @JsonProperty("floor")
        val floor: String,
        @JsonProperty("floor_string")
        val floorString: String,
        @JsonProperty("building_floor")
        val buildingFloor: String,
        @JsonProperty("title")
        val title: String,
        @JsonProperty("is_first_movein")
        val isFirstMovein: Any?,
        @JsonProperty("room_type")
        val roomType: String,
        @JsonProperty("status")
        val status: Boolean,
        @JsonProperty("tags")
        val tags: List<Any>,
        @JsonProperty("service_type")
        val serviceType: String,
        @JsonProperty("random_location")
        val randomLocation: RandomLocation,
        @JsonProperty("manage_cost")
        val manageCost: String,
        @JsonProperty("reg_date")
        val regDate: String,
        @JsonProperty("is_new")
        val isNew: Boolean,
        @JsonProperty("addressOrigin")
        val addressOrigin: AddressOrigin,
        @JsonProperty("action")
        val action: Action,
        @JsonProperty("contract")
        val contract: String,
        @JsonProperty("address")
        val address: String,
        @JsonProperty("is_zzim")
        val isZzim: Boolean,
        @JsonProperty("address1")
        val address1: String,
        @JsonProperty("address2")
        val address2: Any?,
        @JsonProperty("address3")
        val address3: Any?,
        @JsonProperty("item_bm_type")
        val itemBmType: String
    ) {
        data class AreaSupply(
            @JsonProperty("m2")
            val m2: Double,
            @JsonProperty("p")
            val p: String,
        )

        data class AreaContract(
            @JsonProperty("m2")
            val m2: Double,
            @JsonProperty("p")
            val p: String,
        )

        data class AreaIndividual(
            @JsonProperty("m2")
            val m2: Double,
            @JsonProperty("p")
            val p: String,
        )

        data class RandomLocation(
            @JsonProperty("lat")
            val lat: Double,
            @JsonProperty("lng")
            val lng: Double,
        )

        data class AddressOrigin(
            @JsonProperty("local1")
            val local1: String,
            @JsonProperty("local2")
            val local2: String,
            @JsonProperty("local3")
            val local3: String,
            @JsonProperty("local4")
            val local4: String,
            @JsonProperty("fullText")
            val fullText: String,
            @JsonProperty("localText")
            val localText: String,
        )

        data class Action(
            @JsonProperty("isRead")
            val isRead: Boolean,
            @JsonProperty("readAt")
            val readAt: Any?,
            @JsonProperty("isInquired")
            val isInquired: Boolean,
            @JsonProperty("inquiredAt")
            val inquiredAt: Any?,
            @JsonProperty("isRewarded")
            val isRewarded: Boolean,
            @JsonProperty("rewardedAt")
            val rewardedAt: Any?,
            @JsonProperty("isReported")
            val isReported: Boolean,
            @JsonProperty("reportedAt")
            val reportedAt: Any?,
            @JsonProperty("isChecked")
            val isChecked: Boolean,
            @JsonProperty("checkedAt")
            val checkedAt: Any?,
            @JsonProperty("isZzim")
            val isZzim: Boolean,
        )
    }
}

internal data class ZigbangOnreoomItemDetailResponse(
    @JsonProperty("item")
    val item: Item,
) : ExternalDataDetail(source = ExternalDataSource.ZIGBANG_ONEROOM) {
    data class Item(
        @JsonProperty("itemId")
        val itemId: Long,
        @JsonProperty("salesType")
        val salesType: String,
        @JsonProperty("serviceType")
        val serviceType: String,
        @JsonProperty("images")
        val images: List<String>,
        @JsonProperty("imageThumbnail")
        val imageThumbnail: String,
        @JsonProperty("price")
        val price: Price,
        @JsonProperty("area")
        val area: Area,
        @JsonProperty("roomType")
        val roomType: String,
        @JsonProperty("title")
        val title: String,
        @JsonProperty("description")
        val description: String,
        @JsonProperty("status")
        val status: String,
        @JsonProperty("randomLocation")
        val randomLocation: RandomLocation,
        @JsonProperty("parkingAvailableText")
        val parkingAvailableText: String,
        @JsonProperty("parkingCountText")
        val parkingCountText: String,
        @JsonProperty("elevator")
        val elevator: Boolean,
        @JsonProperty("roomDirection")
        val roomDirection: String,
        @JsonProperty("directionCriterion")
        val directionCriterion: String,
        @JsonProperty("moveinDate")
        val moveinDate: String,
        @JsonProperty("pnu")
        val pnu: String,
        @JsonProperty("floor")
        val floor: Floor,
        @JsonProperty("buildingId")
        val buildingId: Long?,
        @JsonProperty("options")
        val options: List<String>,
        @JsonProperty("manageCost")
        val manageCost: ManageCost,
        @JsonProperty("isPremium")
        val isPremium: Boolean,
        @JsonProperty("isHomepage")
        val isHomepage: Boolean,
        @JsonProperty("hasUserPenalty")
        val hasUserPenalty: Boolean,
        @JsonProperty("roomGubunCode")
        val roomGubunCode: String,
        @JsonProperty("viewCount")
        val viewCount: Int,
        @JsonProperty("updatedAt")
        val updatedAt: String,
        @JsonProperty("approveDate")
        val approveDate: String,
        @JsonProperty("bathroomCount")
        val bathroomCount: String,
        @JsonProperty("residenceType")
        val residenceType: String,
        @JsonProperty("addressOrigin")
        val addressOrigin: AddressOrigin,
        @JsonProperty("jibunAddress")
        val jibunAddress: String,
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
        val petAllowed: String?
    ) {
        data class Price(
            @JsonProperty("deposit")
            val deposit: Int,
            @JsonProperty("rent")
            val rent: Int,
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
            val lat: Double,
            @JsonProperty("lng")
            val lng: Double,
        )
        data class Floor(
            @JsonProperty("allFloors")
            val allFloors: String,
            @JsonProperty("floor")
            val floor: String,
        )
        data class ManageCost(
            @JsonProperty("amount")
            val amount: Int,
            @JsonProperty("includes")
            val includes: List<String>,
            @JsonProperty("notIncludes")
            val notIncludes: List<String>,
            @JsonProperty("type")
            val type: String,
        )
        data class AddressOrigin(
            @JsonProperty("local1")
            val local1: String,
            @JsonProperty("local2")
            val local2: String,
            @JsonProperty("local3")
            val local3: String,
            @JsonProperty("local4")
            val local4: String,
            @JsonProperty("fullText")
            val fullText: String,
            @JsonProperty("localText")
            val localText: String,
        )
    }
}

internal data class ZigbangOfficetelItemDetailResponse(
    @JsonProperty("item")
    val item: Item,
) : ExternalDataDetail(source = ExternalDataSource.ZIGBANG_OFFICETEL) {
    data class Item(
        @JsonProperty("itemId")
        val itemId: Long,
        @JsonProperty("salesType")
        val salesType: String,
        @JsonProperty("serviceType")
        val serviceType: String,
        @JsonProperty("images")
        val images: List<String>,
        @JsonProperty("imageThumbnail")
        val imageThumbnail: String,
        @JsonProperty("price")
        val price: Price,
        @JsonProperty("area")
        val area: Area,
        @JsonProperty("roomType")
        val roomType: String,
        @JsonProperty("title")
        val title: String,
        @JsonProperty("description")
        val description: String,
        @JsonProperty("status")
        val status: String,
        @JsonProperty("randomLocation")
        val randomLocation: RandomLocation,
        @JsonProperty("parkingAvailableText")
        val parkingAvailableText: String,
        @JsonProperty("parkingCountText")
        val parkingCountText: String,
        @JsonProperty("elevator")
        val elevator: Boolean,
        @JsonProperty("roomDirection")
        val roomDirection: String,
        @JsonProperty("directionCriterion")
        val directionCriterion: String,
        @JsonProperty("moveinDate")
        val moveinDate: String,
        @JsonProperty("pnu")
        val pnu: String,
        @JsonProperty("floor")
        val floor: Floor,
        @JsonProperty("buildingId")
        val buildingId: Long?,
        @JsonProperty("options")
        val options: List<String>,
        @JsonProperty("manageCost")
        val manageCost: ManageCost,
        @JsonProperty("isPremium")
        val isPremium: Boolean,
        @JsonProperty("isHomepage")
        val isHomepage: Boolean,
        @JsonProperty("hasUserPenalty")
        val hasUserPenalty: Boolean,
        @JsonProperty("roomGubunCode")
        val roomGubunCode: String,
        @JsonProperty("viewCount")
        val viewCount: Int,
        @JsonProperty("updatedAt")
        val updatedAt: String,
        @JsonProperty("approveDate")
        val approveDate: String,
        @JsonProperty("bathroomCount")
        val bathroomCount: String,
        @JsonProperty("residenceType")
        val residenceType: String,
        @JsonProperty("addressOrigin")
        val addressOrigin: AddressOrigin,
        @JsonProperty("jibunAddress")
        val jibunAddress: String,
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
        val petAllowed: String?
    ) {
        data class Price(
            @JsonProperty("deposit")
            val deposit: Int,
            @JsonProperty("rent")
            val rent: Int,
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
            val lat: Double,
            @JsonProperty("lng")
            val lng: Double,
        )
        data class Floor(
            @JsonProperty("allFloors")
            val allFloors: String,
            @JsonProperty("floor")
            val floor: String,
        )
        data class ManageCost(
            @JsonProperty("amount")
            val amount: Int,
            @JsonProperty("includes")
            val includes: List<String>,
            @JsonProperty("notIncludes")
            val notIncludes: List<String>,
            @JsonProperty("type")
            val type: String,
        )
        data class AddressOrigin(
            @JsonProperty("local1")
            val local1: String,
            @JsonProperty("local2")
            val local2: String,
            @JsonProperty("local3")
            val local3: String,
            @JsonProperty("local4")
            val local4: String,
            @JsonProperty("fullText")
            val fullText: String,
            @JsonProperty("localText")
            val localText: String,
        )
    }
}
