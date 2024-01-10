package xyz.bukinator.house.model.embeddable

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class Floor(
    @Column(name = "floor_total")
    @Comment("전체 층수")
    val floorTotal: Int,

    @Column(name = "floor_target")
    @Comment("대상 층수")
    val floorTarget: Int,
)
