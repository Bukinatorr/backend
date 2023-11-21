package xyz.bukinator.api.config

import org.hibernate.internal.util.collections.CollectionHelper.listOf
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.api.domain.house.*
import java.time.LocalDate

//@Profile("local")
@Transactional
@Component
class DatabaseInitializer(
    private val houseRepository: HouseRepository,
) : CommandLineRunner {
    override fun run(vararg args: String) {
        cleanUp()
        populate()
    }

    private fun cleanUp() {}

    private fun populate() {
        createHouse()
    }

    private fun createHouse() {
        val houses = listOf(
            House(
                id = 1,
                origin = Origin(
                    originId = "38702598",
                    originSource = "ZIGBANG",
                    originUpdatedAt = null
                ),
                salesType = "월세",
                houseName = null,
                houseType = "",
                roomType = "오픈형원룸",
                roomDirection = "E",
                thumbnail = "https://ic.zigbang.com/ic/items/38702598/1.jpg",
                images = listOf(
                    "https://ic.zigbang.com/ic/items/38650795/1.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/2.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/3.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/4.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/5.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/6.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/7.jpg",
                    "https://ic.zigbang.com/ic/items/38650795/8.jpg"
                ),
                price = Price(
                    priceDeposit = 1000,
                    priceRent = 75,
                    priceManage = 5,
                    priceManageIncludes = listOf()
                ),
                area = Area(
                    areaContract = null,
                    areaSupply = null,
                    areaIndividual = 21.42
                ),
                title = "삼전역도보3분역세권원룸깔끔한풀옵션",
                description = "공실일때 사진입니다\\r\\n\uD83C\uDF1F 요 약 설 명 \uD83C\uDF1F\\r\\n\\r\\n✔ 위치 : 삼전동 상단 삼전역 인근\\r\\n\\r\\n✔ 가격 : 보증금 1000만원/월세 68만원/관리비 10만원 \\r\\n\\r\\n✔ 구조 : 분리형원룸\\r\\n\\r\\n✔ 옵션 : 에어컨, 냉장고, 세탁기, 가스레인지, 신발장, 싱크대\\r\\n\\r\\n✔ 주차 : 주차가능(유료)\\r\\n\\r\\n✔ 교통 : 삼전역 도보 3분거리\\r\\n\\r\\n\\r\\n\uD83C\uDF1F 상 세 설 명 \uD83C\uDF1F\\r\\n\\r\\n✔ 풀옵션의 편리한 원룸이에요~!\\r\\n\\r\\n✔ 내부가 깔끔하며 방 사이즈가 큰 분리형 원룸이에요~!\\r\\n\\r\\n✔ 에어컨, 냉장고, 세탁기, 가스레인지, 신발장, 싱크대 옵션으로 마련되어 있어요~!\\r\\n\\r\\n✔ 바닥, 벽지상태, 화장실 등 내부컨디션이 깔끔해서 살기 좋아요~!\\r\\n\\r\\n✔ 베란다에 세탁기가 있어 세탁 후 빨래 널기도 좋아요~!\\r\\n\\r\\n✔ 주변 편의시설(마트, 커피숍, 약국 등)이 잘 갖추어져 있어서 생활하기 편해요~!\\r\\n\\r\\n✔ 삼전역 가깝고, 버스 노선 많아요~!\\r\\n\\r\\n✔ 석촌호수공원이 인접하여 산책하기 좋아요~!\\r\\n\\r\\n✔ 유동인구 많은 이면도로와 가까워 귀갓길 안심하셔도 돼요~!\\r\\n\\r\\n✔ 석촌호수카페거리와 가까워 산책 및 여가생활 보내시기에 쾌적한 조건을 갖추고 있어요~!",
                status = HouseStatus.OPEN,
                location = LatLng(
                    locationLat = 37.50563044873152,
                    locationLng = 127.09109118183358
                ),
                parkingCount = 11,
                elevator = false,
                movinDate = LocalDate.parse("2023-11-16"),
                approveDate = LocalDate.parse("2022-10-30"),
                residenceType = "단독주택",
                pnu = "1171010600100130006",
                floor = Floor(
                    floorTarget = 2,
                    floorTotal = 5
                ),
                options = listOf("에어컨","냉장고","세탁기","가스레인지","신발장","싱크대"),
                address = Address(
                    addressLocal1 = "서울시",
                    addressLocal2 = "송파구",
                    addressLocal3 = "삼전동",
                    addressLocal4 = "",
                    addressJibun = "송파구 삼전동 13-6"
                )
            )
        )
        houseRepository.saveAll(houses)
    }
}