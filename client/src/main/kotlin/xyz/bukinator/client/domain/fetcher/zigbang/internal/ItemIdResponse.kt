package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty

internal data class ItemIdResponse(
    @JsonProperty("items")
    val items: List<ItemId>,
) {
    data class ItemId(
        @JsonProperty("lat")
        val lat: Double,
        @JsonProperty("lng")
        val lng: Double,
        @JsonProperty("itemId")
        val itemId: Long,
        @JsonProperty("itemBmType")
        val itemBmType: String,
        @JsonProperty("buildingId")
        val buildingId: Long,
    )
}
