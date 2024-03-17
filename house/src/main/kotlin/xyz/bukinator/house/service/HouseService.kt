package xyz.bukinator.house.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.dto.HouseDto
import xyz.bukinator.house.model.House
import xyz.bukinator.house.repository.HouseRepository
import java.util.*

@Service
class HouseService(
    private val houseRepository: HouseRepository,
) {
    var count = 0

    @Transactional(readOnly = true)
    fun get(id: UUID) = houseRepository.findById(id)

    @Transactional
    fun create(dto: HouseDto): House {
        return houseRepository.save(House.create(dto))
    }

    @Transactional
    fun update(id: UUID, dto: HouseDto): House {
        val house = get(id).orElseThrow { Exception("House not found") }
        house.modify(dto)
        return houseRepository.save(house)
    }

    @Transactional
    fun createOrUpdateAll(houseDtos: List<HouseDto>) {
        val originIds = houseDtos.map { it.originId }
        val existingHouses = houseRepository.findAllByOrigin_OriginIdIn(originIds)?.associateBy { it.origin.originId }

        val newHouses = houseDtos.map { houseDto ->
            existingHouses?.get(houseDto.originId)?.apply { modify(houseDto) } ?: House.create(houseDto)
        }

        println("${newHouses.size}개 넣어야징~")

        houseRepository.saveAll(newHouses)

        count += newHouses.size
        println("count: $count")
    }

//    @Transactional
//    fun delete(id: UUID) {
//        get(id).orElse(null)?.let {
//            it.deletedAt = LocalDateTime.now()
//            houseRepository.save(it)
//        } ?: throw RuntimeException("Not found")
//    }
}
