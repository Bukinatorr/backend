package xyz.bukinator.house.model.embeddable

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment
import xyz.bukinator.common.converter.StringToListConverter

@Embeddable
class Price(
    @Column(name = "price_deposit", nullable = false)
    @Comment("보증금")
    val priceDeposit: Int,

    @Column(name = "price_rent", nullable = false)
    @Comment("월세")
    val priceRent: Int,

    @Column(name = "price_manage", nullable = false)
    @Comment("관리세")
    val priceManage: Int,

    @Column(name = "price_manage_includes")
    @Convert(converter = StringToListConverter::class)
    @Comment("관리비 포함 항목")
    val priceManageIncludes: List<String>?,
)
