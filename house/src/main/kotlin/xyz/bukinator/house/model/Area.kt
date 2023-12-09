package xyz.bukinator.house.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class Area(
    @Column(name = "area_contract")
    @Comment("계약 면적")
    val areaContract: Double?,

    @Column(name = "area_supply")
    @Comment("공급 면적")
    val areaSupply: Double?,

    @Column(name = "area_individual")
    @Comment("전용 면적")
    val areaIndividual: Double?,
) {
    init {
        require(areaContract != null || areaSupply != null || areaIndividual != null) {
            "계약 면적, 공급 면적, 전용 면적 중 한가지는 반드시 필요합니다."
        }
    }
}
