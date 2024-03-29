package xyz.bukinator.house.model

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.hibernate.annotations.GenericGenerator
import xyz.bukinator.client.domain.external.HouseStatus
import xyz.bukinator.client.domain.external.HouseType
import xyz.bukinator.client.domain.external.RoomDirection
import xyz.bukinator.client.domain.external.SalesType
import xyz.bukinator.common.converter.StringToListConverter
import xyz.bukinator.house.dto.HouseDto
import xyz.bukinator.house.model.embeddable.Address
import xyz.bukinator.house.model.embeddable.Area
import xyz.bukinator.house.model.embeddable.Floor
import xyz.bukinator.house.model.embeddable.Location
import xyz.bukinator.house.model.embeddable.Origin
import xyz.bukinator.house.model.embeddable.Price
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "house")
class House(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID(),

    @Embedded
    @Comment("데이터 원천 메타 정보")
    var origin: Origin,

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_type", nullable = false)
    @Comment("판매 타입")
    var salesType: SalesType,

    @Column(name = "house_name")
    @Comment("건물 이름")
    var houseName: String?,

    @Enumerated(EnumType.STRING)
    @Column(name = "house_type")
    @Comment("방 타입")
    var houseType: HouseType,

    @Column(name = "room_type")
    @Comment("방 구조")
    var roomType: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "room_direction")
    @Comment("방 향")
    var roomDirection: RoomDirection,

    @Column(name = "thumbnail")
    @Comment("썸네일 이미지 링크")
    var thumbnail: String,

    @Column(name = "images", length = 5000)
    @Convert(converter = StringToListConverter::class)
    @Comment("방 이미지 링크")
    var images: List<String>,

    @Embedded
    @Comment("방 가격 정보")
    var price: Price,

    @Embedded
    @Comment("방 면적 정보")
    var area: Area,

    @Column(name = "title")
    @Comment("매물 제목")
    var title: String,

    @Column(name = "description", length = 5000)
    @Comment("매물 설명")
    var description: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Comment("매물 상태")
    var status: HouseStatus,

    @Embedded
    @Comment("위치 정보")
    val location: Location,

    @Column(name = "parking_count")
    @Comment("주차 대수")
    var parkingCount: Double,

    @Column(name = "elevator")
    @Comment("승강기 존재 유무")
    var elevator: Boolean,

    @Column(name = "movin_date")
    @Comment("입주일")
    var movinDate: LocalDate,

    @Column(name = "approve_date")
    @Comment("승인일")
    var approveDate: LocalDate?,

    @Column(name = "residence_type")
    @Comment("주거 형태")
    var residenceType: String,

    @Column(name = "pnu")
    @Comment("시군구 코드")
    var pnu: String,

    @Embedded
    @Comment("층수 정보")
    var floor: Floor,

    @Column(name = "options")
    @Convert(converter = StringToListConverter::class)
    @Comment("옵션 정보")
    var options: List<String>,

    @Embedded
    @Comment("주소 정보")
    val address: Address,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null,

    @Column(name = "updated_at", nullable = false, updatable = true)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at", updatable = true)
    var deletedAt: LocalDateTime? = null,
) {
    companion object {
        fun create(dto: HouseDto): House {
            return House(
                origin = Origin(
                    originId = dto.originId,
                    originSource = dto.originSource,
                    originUpdatedAt = dto.originUpdatedAt
                ),
                salesType = dto.salesType,
                houseName = dto.houseName,
                houseType = dto.houseType,
                roomType = dto.roomType,
                roomDirection = dto.roomDirection,
                thumbnail = dto.thumbnail,
                images = dto.images,
                price = Price(dto.priceDeposit, dto.priceRent, dto.priceManage, dto.priceManageIncludes),
                area = Area(dto.areaContract, dto.areaSupply, dto.areaIndividual),
                title = dto.title,
                description = dto.description,
                status = dto.status,
                location = Location(dto.lat, dto.lng),
                parkingCount = dto.parkingCount,
                elevator = dto.elevator,
                movinDate = dto.movinDate,
                approveDate = dto.approveDate,
                residenceType = dto.residenceType,
                pnu = dto.pnu,
                floor = Floor(dto.floorTotal, dto.floorTarget),
                options = dto.options,
                address = Address(dto.addressLocal1, dto.addressLocal2, dto.addressLocal3, dto.addressLocal4, dto.addressJibun),
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    internal fun modify(dto: HouseDto): House {
        this.origin.originUpdatedAt = dto.originUpdatedAt ?: this.origin.originUpdatedAt
        this.houseName = dto.houseName
        this.thumbnail = dto.thumbnail
        this.images = dto.images
        this.price = Price(dto.priceDeposit, dto.priceRent, dto.priceManage, dto.priceManageIncludes ?: this.price.priceManageIncludes)
        this.area = Area(dto.areaContract ?: this.area.areaContract, dto.areaSupply ?: this.area.areaSupply, dto.areaIndividual ?: this.area.areaIndividual)
        this.title = dto.title
        this.description = dto.description
        this.status = dto.status
        this.movinDate = dto.movinDate
        this.pnu = dto.pnu
        this.options = dto.options

        return this
    }
}
