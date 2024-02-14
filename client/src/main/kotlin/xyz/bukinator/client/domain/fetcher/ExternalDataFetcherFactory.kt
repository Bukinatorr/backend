package xyz.bukinator.client.domain.fetcher

import xyz.bukinator.client.domain.fetcher.zigbang.ZigbangOfficetelDataFetcher
import xyz.bukinator.client.domain.fetcher.zigbang.ZigbangOneroomDataFetcher
import xyz.bukinator.client.domain.model.ExternalDataSource

object ExternalDataFetcherFactory {
    fun createFetcher(externalDataSource: ExternalDataSource): ExternalDataFetcher {
        return when (externalDataSource) {
            ExternalDataSource.ZIGBANG_ONEROOM -> ZigbangOneroomDataFetcher()
            ExternalDataSource.ZIGBANG_OFFICETEL -> ZigbangOfficetelDataFetcher()
            else -> throw IllegalArgumentException("Unknown data source")
        }
    }
}
