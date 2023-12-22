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
class OriginHouseDataReader : ItemReader<Any> {
    override fun read(): List<ExternalDataSummary> {
        return fetchZigbangItemSummaries()
    }

    private fun fetchZigbangItemSummaries(): List<ExternalDataSummary> {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)

        val summaries = FETCHABLE_GEOHASH.map { it ->
            val itemIds = zigbangOneroomDataFetcher.fetchItemIds(it)
            zigbangOneroomDataFetcher.fetchItemSummaries(itemIds)
        }

        return summaries
    }

    private fun fetchZigbangItemDetail(itemId: Long): ExternalDataDetail {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        return zigbangOneroomDataFetcher.fetchItemDetail(itemId)
    }

    companion object {
        val FETCHABLE_GEOHASH = listOf<String>("wydm")
    }
}

