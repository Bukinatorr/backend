package xyz.bukinator.batch.core

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component

@Component
@StepScope
class OriginHouseDataReader(
    private val originHouseFetcher: OriginHouseFetcher,
): ItemReader<String> {
    val pageSize = 100

    override fun read(): String {
        val rawData = originHouseFetcher.fetch("Zigbang", pageSize)
        return rawData
    }
}
