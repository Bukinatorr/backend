package xyz.bukinator.house.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class CreateHouseDto(
    val originSource: String,
    val originId: String,
    val originUpdatedAt: LocalDateTime? = null,
    val salesType: String,
    val houseName: String,
    val houseType: String,
    val roomType: String,
    val roomDirection: String,
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
    val lat: Double,
    val lng: Double,
    val parkingCount: Int,
    val elevator: Boolean,
    val movinDate: LocalDate?,
    val approveDate: LocalDate?,
    val residenceType: String,
    val pnu: String,
    val floorTotal: Int,
    val floorTarget: Int,
    val options: List<String>,
    val addressLocal1: String,
    val addressLocal2: String,
    val addressLocal3: String,
    val addressLocal4: String,
    val addressJibun: String,
)
