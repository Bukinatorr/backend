package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

internal data class ZigbangOneroomItemIdResponse (
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
    )
}

internal data class ZigbangOfficetelItemIdResponse (
    @JsonProperty("items")
    val items: List<ItemId>,
) {
    data class ItemId(
        @JsonProperty("buildingId")
        val buildingId: Long,
        @JsonProperty("itemId")
        val itemId: Long,
        @JsonProperty("itemBmType")
        val itemBmType: String,
    )
}

internal data class ZigbangItemSummaryResponse(
    @JsonProperty("items")
    val items: List<JsonNode>,
)
