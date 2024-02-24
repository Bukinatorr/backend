package xyz.bukinator.client.domain.external

enum class ExternalDataSource {
    ZIGBANG_ONEROOM, ZIGBANG_OFFICETEL
}

// 전세라는 용어 자체가 한국에만 있기 때문에 JEONSE이다.
enum class SalesType {
    MONTHLY, JEONSE
}

enum class HouseStatus {
    OPEN, CLOSE
}

enum class RoomDirection {
    EAST, WEST, SOUTH, NORTH, SOUTH_EAST, SOUTH_WEST, NORTH_EAST, NORTH_WEST
}
