package xyz.bukinator.client.domain.fetcher

import com.fasterxml.jackson.databind.JsonNode

interface ExternalDataFetcher {
    fun fetchItemIds(geohash: String): List<Long>
    fun fetchItemIds(lat: Long, lng: Long): List<Long>
    fun fetchItemSummaries(itemIds: List<Long>): List<JsonNode>
    fun fetchItemDetail(itemId: Long): JsonNode
}
