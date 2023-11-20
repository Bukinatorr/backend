package xyz.bukinator.batch.zigbang.oneroom

import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Service
import xyz.bukinator.batch.bukinator.House

@Service
class OneRoomItemProcessor : ItemProcessor<List<OneRoomItemList>, List<House>> {
    override fun process(item: List<OneRoomItemList>): List<House>? {
        return item.map {
            it.toHouse()
        }
    }
}