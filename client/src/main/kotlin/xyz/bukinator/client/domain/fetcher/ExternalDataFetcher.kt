package xyz.bukinator.client.domain.fetcher

import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSummary

interface ExternalDataFetcher {
    fun fetchItemIds(geohash: String): List<Long>
    fun fetchItemIds(lat: Long, lng: Long): List<Long>
    fun fetchItemSummaries(itemIds : List<Long>): ExternalDataSummary
    fun fetchItemDetail(itemId : Long): ExternalDataDetail
}