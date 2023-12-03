package xyz.bukinator.client.domain.fetcher

import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSummary
import xyz.bukinator.client.domain.model.LocationCriteria

interface ExternalDataFetcher {
    fun fetchItemIds(locationCriteria: LocationCriteria): List<Long>
    fun fetchItemSummaries(itemIds : List<Long>): List<ExternalDataSummary>
    fun fetchItemDetail(itemId : Long): ExternalDataDetail
}