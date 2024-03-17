package xyz.bukinator.house.dto

import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.client.domain.external.HouseStatus
import xyz.bukinator.client.domain.external.HouseType
import xyz.bukinator.client.domain.external.SalesType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class HouseDto(
    val id: UUID,
    val originSource: String,
    val originId: String,
    val originUpdatedAt: LocalDateTime? = null,
    val salesType: SalesType,
    val houseName: String,
    val houseType: HouseType,
    val roomType: String,
    val roomDirection: String,
    val thumbnail: String,
    val images: List<String>,
    val priceDeposit: Int,
    val priceRent: Int,
    val priceManage: Double,
    val priceManageIncludes: List<String>? = null,
    val areaContract: Double?,
    val areaSupply: Double?,
    val areaIndividual: Double?,
    val title: String,
    val description: String,
    val status: HouseStatus,
    val lat: Double,
    val lng: Double,
    val parkingCount: Int,
    val elevator: Boolean,
    val movinDate: LocalDate,
    val approveDate: LocalDate,
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
) {
    companion object {
        fun of(origin: ExternalDataSummary): HouseDto {
            return HouseDto(
                id = UUID.randomUUID(),
                originSource = "ZIGBANG",
                originId = origin.getItemId(),
                salesType = origin.getSalesType(),
                houseName = "",
                houseType = origin.getHouseType(),
                roomType = origin.getRoomType() ?: "-1",
                roomDirection = "",
                thumbnail = origin.getThumbnail() ?: "",
                images = listOf(),
                priceDeposit = origin.getPriceDeposit() ?: 0,
                priceRent = origin.getPriceRent() ?: 0,
                priceManage = origin.getPriceManage() ?: 0.0,
                priceManageIncludes = listOf(),
                areaContract = origin.getAreaContract(),
                areaSupply = origin.getAreaSupply(),
                areaIndividual = origin.getAreaIndividual(),
                title = origin.getTitle() ?: "",
                description = "",
                status = origin.getStatus(),
                lat = origin.getLocationLat() ?: 0.0,
                lng = origin.getLocationLng() ?: 0.0,
                parkingCount = 0,
                elevator = false,
                movinDate = LocalDate.now(),
                approveDate = LocalDate.now(),
                residenceType = "",
                pnu = "",
                floorTotal = origin.getFloorTotal() ?: 0,
                floorTarget = origin.getFloorTarget() ?: 0,
                options = listOf(),
                addressLocal1 = origin.getAddressLocal1() ?: "",
                addressLocal2 = origin.getAddressLocal2() ?: "",
                addressLocal3 = origin.getAddressLocal3() ?: "",
                addressLocal4 = origin.getAddressLocal4() ?: "",
                addressJibun = ""
            )
        }
    }
}
