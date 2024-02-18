package xyz.bukinator.house.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import xyz.bukinator.house.model.House
import java.util.UUID

@Repository
interface HouseRepository : JpaRepository<House, UUID>, JpaSpecificationExecutor<House> {
    fun findAllByOrigin_OriginIdIn(OriginIds: List<String>): List<House>?
}
