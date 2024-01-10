package xyz.bukinator.batch.core

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import xyz.bukinator.client.domain.fetcher.ExternalDataFetcherFactory
import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSource
import xyz.bukinator.client.domain.model.ExternalDataSummary

@Component
@StepScope
class OriginHouseDataReader : ItemReader<ExternalDataSummary> {
    override fun read(): ExternalDataSummary {
        return fetchZigbangItemSummaries()
    }

    private fun fetchZigbangItemSummaries(): ExternalDataSummary {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        val itemIds = zigbangOneroomDataFetcher.fetchItemIds(FETCHABLE_GEOHASH)
        return zigbangOneroomDataFetcher.fetchItemSummaries(itemIds)
    }

    private fun fetchZigbangItemDetail(itemId: Long): ExternalDataDetail {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        return zigbangOneroomDataFetcher.fetchItemDetail(itemId)
    }

    companion object {
        const val FETCHABLE_GEOHASH = "wydm"
    }
}
