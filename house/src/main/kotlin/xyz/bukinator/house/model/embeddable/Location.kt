package xyz.bukinator.house.model.embeddable

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class Location(
    @Column(name = "location_lat", nullable = false)
    @Comment("위도")
    val lat: Double,

    @Column(name = "location_lng", nullable = false)
    @Comment("경도")
    val lng: Double,
)
