package xyz.bukinator.client.domain.fetcher.zigbang

import xyz.bukinator.client.domain.external.ExternalDataDetail
import xyz.bukinator.client.domain.external.ExternalDataFetcher
import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.client.domain.fetcher.zigbang.internal.DataFetcher
import xyz.bukinator.client.domain.model.ZigbangDataDetail
import xyz.bukinator.client.domain.model.ZigbangDataSummary

internal class ZigbangOneroomDataFetcher : ExternalDataFetcher {
    private val dataFetcher = DataFetcher()

    override fun fetchItemIds(geohash: String): List<Long> {
        return dataFetcher.fetchItemIds("oneroom", geohash).items.map { it.itemId }
    }

    override fun fetchItemIds(lat: Long, lng: Long): List<Long> {
        throw Exception("geohash만 지원")
    }

    override fun fetchItemSummaries(itemIds: List<Long>): List<ExternalDataSummary> {
        return dataFetcher.fetchItemList(itemIds).items.map { itemSummary ->
            ZigbangDataSummary(itemSummary)
        }
    }

    override fun fetchItemDetail(itemId: Long): ExternalDataDetail {
        return ZigbangDataDetail(dataFetcher.fetchItemDetail(itemId).item)
    }
}
