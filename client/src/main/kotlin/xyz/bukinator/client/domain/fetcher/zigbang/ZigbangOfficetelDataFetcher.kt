package xyz.bukinator.client.domain.fetcher.zigbang

import xyz.bukinator.client.domain.fetcher.ExternalDataFetcher
import xyz.bukinator.client.domain.fetcher.zigbang.internal.ZigbangDataFetcher
import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSource
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
        val itemSummaies = zigbangDataFetcher.fetchItemList(itemIds)?.items ?: throw Exception("fetchItemSummaries failed")
        return ExternalDataSummary(ExternalDataSource.ZIGBANG_OFFICETEL, itemSummaies)
    }

    override fun fetchItemDetail(itemId : Long): ExternalDataDetail {
        val itemDetail = zigbangDataFetcher.fetchItemDetail(itemId)?.items ?: throw Exception("fetchItemDetail failed")
        return ExternalDataDetail(ExternalDataSource.ZIGBANG_OFFICETEL, itemDetail)
    }
}
