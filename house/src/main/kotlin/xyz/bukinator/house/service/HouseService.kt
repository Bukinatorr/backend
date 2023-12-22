package xyz.bukinator.house.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.domain.House
import xyz.bukinator.house.domain.HouseRepository
import xyz.bukinator.house.dto.CreateHouseDto
import xyz.bukinator.house.dto.UpdateHouseDto

@Service
class HouseService(
    private val houseRepository: HouseRepository,
) {
    @Transactional
    fun createHouse(dto: CreateHouseDto): House {
        return houseRepository.save(House.create(dto))
    }

    @Transactional
    fun updateHouse(id: Long, dto: UpdateHouseDto): House {
        val house = houseRepository.findById(id)
            .orElseThrow<RuntimeException> { RuntimeException("존재하지 않는 House 입니다: $id") }
        house.modify(dto)

        return houseRepository.save(house)
    }

    @Transactional
    fun deleteHouse(id: Long) {
        houseRepository.deleteById(id)
    }
}
