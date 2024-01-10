package xyz.bukinator.house.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.dto.CreateHouseDto
import xyz.bukinator.house.dto.UpdateHouseDto
import xyz.bukinator.house.model.House
import xyz.bukinator.house.repository.HouseRepository
import java.lang.RuntimeException
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
        return houseRepository.findById(id).orElse(null)?.let {
            houseRepository.save(it.modify(dto))
        } ?: throw Exception("Not found")
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
        } ?: throw RuntimeException("Not found")
    }
}
