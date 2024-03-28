package xyz.bukinator.batch.core

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import xyz.bukinator.client.domain.external.ExternalDataDetail
import xyz.bukinator.client.domain.external.ExternalDataFetcherFactory
import xyz.bukinator.client.domain.external.ExternalDataSource
import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.house.dto.HouseDto

@Component
@StepScope
class OriginHouseDataProcessor : ItemProcessor<List<ExternalDataSummary>, List<HouseDto>> {
    override fun process(externalDataSummaries: List<ExternalDataSummary>): List<HouseDto> {
        println("Processing ${externalDataSummaries.size} items")
        return externalDataSummaries.map { enhance(it) }
    }

    private fun enhance(externalDataSummary: ExternalDataSummary): HouseDto {
        val itemId = externalDataSummary.getItemId()
        val detailData = fetchZigbangItemDetail(itemId.toLong())
        return HouseDto.of(externalDataSummary).enhancedWith(detailData)
    }

    private fun fetchZigbangItemDetail(itemId: Long): ExternalDataDetail {
        val zigbangOneroomDataFetcher = ExternalDataFetcherFactory.createFetcher(ExternalDataSource.ZIGBANG_ONEROOM)
        return zigbangOneroomDataFetcher.fetchItemDetail(itemId)
    }
}
