package xyz.bukinator.house.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class LatLng(
    @Column(name = "location_lat", nullable = false)
    @Comment("위도")
    val locationLat: Double,

    @Column(name = "location_lng", nullable = false)
    @Comment("경도")
    val locationLng: Double
)