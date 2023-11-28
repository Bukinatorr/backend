package xyz.bukinator.house.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.Where
import java.time.LocalDate

@Entity
@Table(name = "house")
@Where(clause = "deleted_at is null")
data class House (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long,

    @Embedded
    @Comment("데이터 원천 메타 정보")
    val origin: Origin,

    @Column(name = "sales_type", nullable = false)
    @Comment("판매 타입")
    val salesType: String,

    @Column(name = "house_name")
    @Comment("건물 이름")
    val houseName: String?,

    @Column(name = "house_type")
    @Comment("방 타입")
    val houseType: String,

    @Column(name = "room_type")
    @Comment("방 구조")
    val roomType: String,

    @Column(name = "room_direction")
    @Comment("방 향")
    val roomDirection: String,

    @Column(name = "thumbnail")
    @Comment("썸네일 이미지 링크")
    val thumbnail: String,

    @Column(name = "images", length = 5000)
    @Convert(converter = StringToListConverter::class)
    @Comment("방 이미지 링크")
    val images: List<String>,

    @Embedded
    @Comment("방 가격 정보")
    val price: Price,

    @Embedded
    @Comment("방 면적 정보")
    val area: Area,

    @Column(name = "title")
    @Comment("매물 제목")
    val title: String,

    @Column(name = "description", length = 5000)
    @Comment("매물 설명")
    val description: String,

    @Column(name = "status")
    @Comment("매물 상태")
    val status: HouseStatus,

    @Embedded
    @Comment("위치 정보")
    val location: LatLng,

    @Column(name = "parking_count")
    @Comment("주차 대수")
    val parkingCount: Int,

    @Column(name = "elevator")
    @Comment("승강기 존재 유무")
    val elevator: Boolean,

    @Column(name = "movin_date")
    @Comment("입주일")
    val movinDate: LocalDate,

    @Column(name = "approve_date")
    @Comment("승인일")
    val approveDate: LocalDate,

    @Column(name = "residence_type")
    @Comment("주거 형태")
    val residenceType: String,

    @Column(name = "pnu")
    @Comment("시군구 코드")
    val pnu: String,

    @Embedded
    @Comment("층수 정보")
    val floor: Floor,

    @Column(name = "options")
    @Convert(converter = StringToListConverter::class)
    @Comment("옵션 정보")
    val options: List<String>,

    @Embedded
    @Comment("주소 정보")
    val address: Address
) : BaseEntity<Long>()