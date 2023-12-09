package xyz.bukinator.house.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class Address(
    @Column(name = "address_local1", nullable = false)
    @Comment("시 정보")
    val addressLocal1: String,

    @Column(name = "address_local2", nullable = false)
    @Comment("군,구 정보")
    val addressLocal2: String,

    @Column(name = "address_local3", nullable = false)
    @Comment("동 정보")
    val addressLocal3: String,

    @Column(name = "address_local4")
    @Comment("리 정보")
    val addressLocal4: String,

    @Column(name = "address_jibun", nullable = false)
    @Comment("지번 주소")
    val addressJibun: String,
)
