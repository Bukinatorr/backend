package xyz.bukinator.house.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.model.House
import xyz.bukinator.house.repository.HouseRepository
import java.time.LocalDateTime
import java.util.UUID

@Service
class HouseService(
    val houseRepository: HouseRepository,
) {
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
        } ?: throw Exception("Not found")
    }
}
