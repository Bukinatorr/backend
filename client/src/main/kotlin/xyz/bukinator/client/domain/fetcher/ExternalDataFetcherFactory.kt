package xyz.bukinator.client.domain.fetcher

import xyz.bukinator.client.domain.fetcher.zigbang.ZigbangOneroomDataFetcher
import xyz.bukinator.client.domain.model.ExternalDataSource

object ExternalDataFetcherFactory {
    fun createFetcher(externalDataSource: ExternalDataSource): ExternalDataFetcher {
        return when (externalDataSource) {
            ExternalDataSource.ZIGBANG -> ZigbangOneroomDataFetcher()
            else -> throw IllegalArgumentException("Unknown data source")
        }
    }
}
