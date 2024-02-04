package xyz.bukinator.client.domain.fetcher.zigbang

import com.fasterxml.jackson.databind.JsonNode
import xyz.bukinator.client.domain.fetcher.ExternalDataFetcher
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ZigbangDataFetcher

class ZigbangOneroomDataFetcher : ExternalDataFetcher {
    private val zigbangDataFetcher = ZigbangDataFetcher()

    override fun fetchItemIds(geohash: String): List<Long> {
        return zigbangDataFetcher.fetchOneroomItemIds(geohash).items.map { it.itemId }
    }

    override fun fetchItemIds(lat: Long, lng: Long): List<Long> {
        throw Exception("geohash만 지원")
    }

    override fun fetchItemSummaries(itemIds: List<Long>): List<JsonNode> {
        return zigbangDataFetcher.fetchOneroomItemList(itemIds).items
    }

    override fun fetchItemDetail(itemId: Long): JsonNode {
        return  zigbangDataFetcher.fetchOneroomItemDetail(itemId)
    }
}
