package xyz.bukinator.client.domain.fetcher

import xyz.bukinator.client.domain.fetcher.zigbang.ZigbangDataFetcher
import xyz.bukinator.client.domain.model.ExternalDataSource

object ExternalDataFetcherFactory {
    fun createFetcher(externalDataSource: ExternalDataSource): ExternalDataFetcher {
        return when (externalDataSource) {
            ExternalDataSource.ZIGBANG -> ZigbangDataFetcher()
            else -> throw IllegalArgumentException("Unknown data source")
        }
    }
}
