package xyz.bukinator.client.domain.fetcher.zigbang

import xyz.bukinator.client.domain.fetcher.ExternalDataFetcher
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ZigbangDataFetcher
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ZigbangOfficetelItemDetailResponse
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ZigbangOfficetelItemListResponse
import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSummary

class ZigbangOfficetelDataFetcher : ExternalDataFetcher {
    private val zigbangDataFetcher = ZigbangDataFetcher()

    override fun fetchItemIds(geohash: String): List<Long> {
        return zigbangDataFetcher.fetchOfficetelItemIds(geohash)?.items?.map { it.itemId } ?: throw Exception("fetchItemIds failed")
    }

    override fun fetchItemIds(lat: Long, lng: Long): List<Long> {
        throw Exception("geohash만 지원")
    }

    override fun fetchItemSummaries(itemIds: List<Long>): ExternalDataSummary {
        return zigbangDataFetcher.fetchOfficetelItemList(itemIds) as ZigbangOfficetelItemListResponse
    }

    override fun fetchItemDetail(itemId: Long): ExternalDataDetail {
        return  zigbangDataFetcher.fetchOfficetelItemDetail(itemId) as ZigbangOfficetelItemDetailResponse
    }
}
