package xyz.bukinator.client.domain.external

interface ExternalDataDetail {
    // metadata
    fun getItemId(): String?
    fun getThumbnail(): String?
    fun getTitle(): String?
    fun getStatus(): HouseStatus?
    fun getHouseName(): String?
    fun getImages(): List<String>?
    fun getDescription(): String?
    fun getMoveinDate(): String?
    fun getApproveDate(): String?
    fun getPnu(): String?
    fun getOptions(): List<String>?

    // 집 정보
    fun getSalesType(): SalesType?
    fun getHouseType(): String?
    fun getAreaContract(): Double?
    fun getAreaSupply(): Double?
    fun getAreaIndividual(): Double?
    fun getFloorTotal(): Int?
    fun getFloorTarget(): Int?
    fun getRoomType(): String?
    fun getRoomDirection(): RoomDirection?
    fun getParkingCount(): Double?
    fun hasElevator(): Boolean?
    fun getResidenceType(): String?

    // 위치 정보
    fun getLocationLat(): Double?
    fun getLocationLng(): Double?
    fun getAddressLocal1(): String?
    fun getAddressLocal2(): String?
    fun getAddressLocal3(): String?
    fun getAddressLocal4(): String?
    fun getAddressJibun(): String?

    // 돈
    fun getPriceDeposit(): Int?
    fun getPriceRent(): Int?
    fun getPriceManage(): Double?
    fun getPriceManageIncludes(): List<String>?
}
