package xyz.bukinator.batch.bukinator

import jakarta.persistence.*
import java.sql.Timestamp

interface ConvertibleToHouse {
    fun toHouse(): House
}

@Entity(name = "house")
data class House (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "origin_source")
    val originSource: String = "",

    @Column(name = "origin_id")
    val originId: String = "",

    @Column(name = "origin_updated_at")
    val originUpdatedAt: Timestamp? = null,

    @Column(name = "sales_type")
    val salesType: String = "",

    @Column(name = "house_name")
    val houseName: String? = null,

    @Column(name = "house_type")
    val houseType: String = "",

    @Column(name = "room_type")
    val roomType: String? = null,

    @Column(name = "room_direction")
    val roomDirection: String? = null,

    @Column(name = "thumbnail")
    val thumbnail: String? = null,

    @Column(name = "images")
    val images: String = "",

    @Column(name = "price_deposit")
    val priceDeposit: Int = 0,

    @Column(name = "price_rent")
    val priceRent: Int = 0,

    @Column(name = "price_manage")
    val priceManage: Int = 0,

    @Column(name = "price_manage_includes")
    val priceManageIncludes: String = "",

    @Column(name = "area_contract")
    val areaContract: Double? = null,

    @Column(name = "area_supply")
    val areaSupply: Double? = null,

    @Column(name = "area_individual")
    val areaIndividual: Double? = null,

    @Column(name = "title")
    val title: String = "",

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "status")
    val status: Byte = 0,

    @Column(name = "location_lat")
    val locationLat: Double = 0.0,

    @Column(name = "location_lng")
    val locationLng: Double = 0.0,

    @Column(name = "parking_count")
    val parkingCount: Int? = null,

    @Column(name = "elevator")
    val elevator: Boolean? = null,

    @Column(name = "movein_date")
    val moveInDate: Timestamp? = null,

    @Column(name = "approve_date")
    val approveDate: Timestamp? = null,

    @Column(name = "residence_type")
    val residenceType: Byte? = null,

    @Column(name = "pnu")
    val pnu: String? = null,

    @Column(name = "floor_total")
    val floorTotal: Int? = null,

    @Column(name = "floor_target")
    val floorTarget: Int? = null,

    @Column(name = "options")
    val options: String = "",

    @Column(name = "address_local1")
    val addressLocal1: String = "",

    @Column(name = "address_local2")
    val addressLocal2: String = "",

    @Column(name = "address_local3")
    val addressLocal3: String = "",

    @Column(name = "address_local4")
    val addressLocal4: String? = null,

    @Column(name = "address_jibun")
    val addressJibun: String = "",

    @Column(name = "created_at")
    val createdAt: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(name = "updated_at")
    val updatedAt: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(name = "deleted_at")
    val deletedAt: Timestamp? = null
)