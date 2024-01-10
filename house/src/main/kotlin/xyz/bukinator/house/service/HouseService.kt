package xyz.bukinator.house.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.dto.CreateHouseDto
import xyz.bukinator.house.dto.UpdateHouseDto
import xyz.bukinator.house.model.House
import xyz.bukinator.house.repository.HouseRepository
import java.time.LocalDateTime
import java.util.*

@Service
class HouseService(
    private val houseRepository: HouseRepository,
) {
    @Transactional
    fun createHouse(dto: CreateHouseDto): House {
        return houseRepository.save(House.create(dto))
    }

    @Transactional
    fun updateHouse(id: UUID, dto: UpdateHouseDto): House {
        val house = houseRepository.findById(id).orElse(null)
            ?: throw RuntimeException("존재하지 않는 House 입니다: $id")
        house.modify(dto)

        return houseRepository.save(house)
    }

    @Transactional
    fun deleteHouse(id: UUID) {
        houseRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun get(id: UUID) = houseRepository.findById(id)

    @Transactional
    fun create(house: House): House = houseRepository.save(house)

    @Transactional
    fun delete(id: UUID) {
        get(id).orElse(null)?.let {
            it.deletedAt = LocalDateTime.now()
            houseRepository.save(it)
            // TODO: Define Exception
        } ?: throw RuntimeException("Not found")
    }
}
