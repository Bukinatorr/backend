package xyz.bukinator.api.application.dto.response

import xyz.bukinator.api.domain.house.House
import java.util.*

data class HouseResponse(
    val data: Optional<House>
){
    companion object
}