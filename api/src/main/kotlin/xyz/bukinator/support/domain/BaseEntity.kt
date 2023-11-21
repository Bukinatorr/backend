package xyz.bukinator.support.domain

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity<ID : Serializable?> {
    abstract val id: ID

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, updatable = true)
    var updatedAt: LocalDateTime? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", updatable = true)
    var deletedAt: LocalDateTime? = null
}