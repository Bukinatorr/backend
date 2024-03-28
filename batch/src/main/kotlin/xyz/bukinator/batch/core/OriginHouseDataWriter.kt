package xyz.bukinator.batch.core

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import xyz.bukinator.client.domain.external.ExternalDataDetail
import xyz.bukinator.client.domain.external.ExternalDataFetcherFactory
import xyz.bukinator.client.domain.external.ExternalDataSource
import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.house.dto.HouseDto
import xyz.bukinator.house.service.HouseService

@Component
@StepScope
class OriginHouseDataWriter(
    private val houseService: HouseService
) : ItemWriter<List<HouseDto>> {
    override fun write(houseDtoChunk: Chunk<out List<HouseDto>>) {
        houseService.createOrUpdateAll(houseDtoChunk.flatMap { it })
    }
}
