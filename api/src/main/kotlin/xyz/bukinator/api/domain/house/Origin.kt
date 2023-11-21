package xyz.bukinator.api.domain.house

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Embeddable
class Origin(
    @Column(name = "origin_source", nullable = false)
    @Comment("데이터 출처")
    val originSource: String,

    @Column(name = "origin_id", nullable = false)
    @Comment("데이터 출처 ID")
    val originId: String,

    @Column(name = "origin_updated_at")
    @Comment("데이터 출처 update_at 정보")
    val originUpdatedAt: LocalDateTime?
) {

}