package xyz.bukinator.batch.zigbang.oneroom

import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Service
import xyz.bukinator.batch.bukinator.House
import xyz.bukinator.batch.bukinator.HouseRepository

@Service
class OneRoomItemWriter(
    private val houseRepository: HouseRepository
) : ItemWriter<List<House>> {
    override fun write(chunk : Chunk<out List<House>>) {
        chunk.forEach { items ->
            houseRepository.saveAllAndFlush(items)
        }
    }
}