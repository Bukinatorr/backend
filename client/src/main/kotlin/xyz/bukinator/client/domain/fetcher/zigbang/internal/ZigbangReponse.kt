package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.google.gson.JsonObject

internal data class OneroomItemIdResponse (
    val items: List<Item>,
) {
    data class Item(
        val lat: Double,
        val lng: Double,
        val itemId: Long,
        val itemBmType: String,
    )
}

internal data class OfficetelItemIdResponse (
    val items: List<Item>,
) {
    data class Item(
        val buildingId: Long,
        val itemId: Long,
        val itemBmType: String,
    )
}

internal data class ItemListResponse (
    val items: List<JsonObject>
)

internal data class ItemDetailResponse (
    val item: JsonObject
)