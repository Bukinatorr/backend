package xyz.bukinator.house.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class UpdateHouseDto(
    val originUpdatedAt: LocalDateTime? = null,
    val houseName: String,
    val thumbnail: String,
    val images: List<String>,
    val priceDeposit: Int,
    val priceRent: Int,
    val priceManage: Int,
    val priceManageIncludes: List<String>? = null,
    val areaContract: Double?,
    val areaSupply: Double?,
    val areaIndividual: Double?,
    val title: String,
    val description: String,
    val status: String,
    val movinDate: LocalDate,
    val pnu: String,
    val options: List<String>,
)
