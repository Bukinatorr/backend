package xyz.bukinator.house.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.dto.HouseDto
import xyz.bukinator.house.model.House
import xyz.bukinator.house.repository.HouseRepository
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.util.*

@Service
class HouseService(
    private val houseRepository: HouseRepository,
) {
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

        houseDtos.forEach { houseDto ->
            val existingHouse = existingHouses?.get(houseDto.originId)
            if (existingHouse != null) {
                existingHouse.modify(houseDto)
                houseRepository.save(existingHouse)
            } else {
                houseRepository.save(House.create(houseDto))
            }
        }
    }

    @Transactional
    fun delete(id: UUID) {
        get(id).orElse(null)?.let {
            it.deletedAt = LocalDateTime.now()
            houseRepository.save(it)
        } ?: throw RuntimeException("Not found")
    }
}
