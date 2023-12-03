package xyz.bukinator.client.domain.fetcher.zigbang

import xyz.bukinator.client.domain.fetcher.ExternalDataFetcher
import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSummary
import xyz.bukinator.client.domain.model.LocationCriteria

class ZigbangDataFetcher : ExternalDataFetcher {
    override fun fetchItemIds(locationCriteria: LocationCriteria): List<Long> {
        return listOf(1, 2, 3)
    }

    override fun fetchItemSummaries(itemIds: List<Long>): List<ExternalDataSummary> {
        return listOf(ExternalDataSummary(), ExternalDataSummary(), ExternalDataSummary())
    }

    override fun fetchItemDetail(itemId : Long): ExternalDataDetail {
        return ExternalDataDetail()
    }
}