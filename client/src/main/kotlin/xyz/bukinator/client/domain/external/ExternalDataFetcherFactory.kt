package xyz.bukinator.client.domain.external

import xyz.bukinator.client.domain.fetcher.zigbang.ZigbangOfficetelDataFetcher
import xyz.bukinator.client.domain.fetcher.zigbang.ZigbangOneroomDataFetcher

object ExternalDataFetcherFactory {
    fun createFetcher(externalDataSource: ExternalDataSource): ExternalDataFetcher {
        return when (externalDataSource) {
            ExternalDataSource.ZIGBANG_ONEROOM -> ZigbangOneroomDataFetcher()
            ExternalDataSource.ZIGBANG_OFFICETEL -> ZigbangOfficetelDataFetcher()
            else -> throw IllegalArgumentException("Unknown data source")
        }
    }
}
