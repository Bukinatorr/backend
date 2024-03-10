package xyz.bukinator.batch.core

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component
import xyz.bukinator.client.domain.fetcher.ExternalDataFetcherFactory
import xyz.bukinator.client.domain.model.ExternalDataSource
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Component
@StepScope
class OriginHouseDataReader : ItemReader<List<JsonNode>> {
    private val geohashIterator = FETCHABLE_GEOHASH.iterator()

    override fun read(): List<JsonNode>? {
        if (!geohashIterator.hasNext()) {
            return null
        }

        val geohash = geohashIterator.next()
        return fetchZigbangItemSummaries(geohash)
    }

    private fun fetchZigbangItemSummaries(geohash: String): List<JsonNode> {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        val itemIds = zigbangOneroomDataFetcher.fetchItemIds(geohash)
        return zigbangOneroomDataFetcher.fetchItemSummaries(itemIds)
    }

    private fun fetchZigbangItemDetail(itemId: Long): JsonNode {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        return zigbangOneroomDataFetcher.fetchItemDetail(itemId)
    }

    companion object {
        val FETCHABLE_GEOHASH = arrayOf("wydm2")
    }
}
