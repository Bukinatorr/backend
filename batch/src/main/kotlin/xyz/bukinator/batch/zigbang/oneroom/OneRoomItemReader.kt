package xyz.bukinator.batch.zigbang.oneroom

import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class OneRoomItemReader (
    private val oneRoomFetcher: OneRoomItemFetcher
) : ItemReader<List<OneRoomItemList>> {
    val itemIds: Queue<Long> = LinkedList()
    val chunkSize = 100

    fun idsInit() {
        itemIds.clear()
        oneRoomFetcher.getItemIds()?.let { ids ->
            ids.forEach { id ->
                itemIds.add(id)
            }
        }
    }

    override fun read(): List<OneRoomItemList>? {
        return if(!itemIds.isEmpty()) {
            // 개발단계에서 일단 딜레이 박음
            Thread.sleep(100)

            val chunkedItemIds = mutableListOf<Long>()
            repeat(minOf(chunkSize, itemIds.size)) {
                itemIds.poll()?.let {
                    chunkedItemIds.add(it)
                }
            }

            oneRoomFetcher.getItemList(chunkedItemIds)
        } else {
            null
        }
    }
}