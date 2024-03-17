package xyz.bukinator.batch.core

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import xyz.bukinator.client.domain.external.ExternalDataDetail
import xyz.bukinator.client.domain.external.ExternalDataFetcherFactory
import xyz.bukinator.client.domain.external.ExternalDataSource
import xyz.bukinator.client.domain.external.ExternalDataSummary

@Component
@StepScope
class OriginHouseDataReader : ItemReader<List<ExternalDataSummary>> {
    private val geohashIterator = FETCHABLE_GEOHASH.iterator()

    override fun read(): List<ExternalDataSummary>? {
        if (!geohashIterator.hasNext()) {
            return null
        }

        val geohash = geohashIterator.next()
        return fetchZigbangItemSummaries(geohash)
    }

    private fun fetchZigbangItemSummaries(geohash: String): List<ExternalDataSummary> {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        val itemIds = zigbangOneroomDataFetcher.fetchItemIds(geohash)
        return zigbangOneroomDataFetcher.fetchItemSummaries(itemIds)
    }

    private fun fetchZigbangItemDetail(itemId: Long): ExternalDataDetail {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        return zigbangOneroomDataFetcher.fetchItemDetail(itemId)
    }

    companion object {
        val FETCHABLE_GEOHASH = arrayOf(
            "wydmb", "wydmc", "wydmf", "wydmg", "wydmu", "wydmy",
            "wydm8", "wydm9", "wydmd", "wydme", "wydms", "wydmt",
            "wydm2", "wydm3", "wydm6", "wydm7", "wydmk", "wydmm",
            "wydm0", "wydm1", "wydm4", "wydm5", "wydmh", "wydmj"
        )
    }
}
