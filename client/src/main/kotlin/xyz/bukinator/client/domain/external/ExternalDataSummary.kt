package xyz.bukinator.client.domain.external

interface ExternalDataSummary {
    // metadata
    fun getItemId(): String
    fun getThumbnail(): String?
    fun getTitle(): String?
    fun getStatus(): HouseStatus

    // 집 정보
    fun getSalesType(): SalesType
    fun getHouseType(): HouseType
    fun getAreaContract(): Double?
    fun getAreaSupply(): Double?
    fun getAreaIndividual(): Double?
    fun getFloorTotal(): Int?
    fun getFloorTarget(): Int?

    // 위치 정보
    fun getLocationLat(): Double?
    fun getLocationLng(): Double?
    fun getAddressLocal1(): String?
    fun getAddressLocal2(): String?
    fun getAddressLocal3(): String?
    fun getAddressLocal4(): String?

    // 돈
    fun getPriceDeposit(): Int?
    fun getPriceRent(): Int?
    fun getPriceManage(): Double?
}
