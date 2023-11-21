package xyz.bukinator.api.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.api.application.dto.response.HouseResponse
import xyz.bukinator.api.application.dto.response.HouseSummaryResponse
import xyz.bukinator.api.application.dto.response.HousesResponse
import xyz.bukinator.api.domain.house.HouseRepository

@Transactional
@Service
class HouseService(
    private val houseRepository: HouseRepository
) {
    fun findAll(): HousesResponse {
        return HousesResponse(
            houseRepository.findAll().map { HouseSummaryResponse(it) }
        )
    }

    fun findById(id: Long): HouseResponse {
        return HouseResponse(
            houseRepository.findById(id)
        )
    }
}