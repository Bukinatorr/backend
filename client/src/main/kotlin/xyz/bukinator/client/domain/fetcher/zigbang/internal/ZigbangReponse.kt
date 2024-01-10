package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.JsonObject

internal data class OneroomItemIdResponse(
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

internal data class OfficetelItemIdResponse(
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

internal data class ItemListResponse(
    @JsonProperty("items")
    val items: List<JsonObject>,
)

internal data class ItemDetailResponse(
    @JsonProperty("item")
    val item: JsonObject,
)
