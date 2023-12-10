package xyz.bukinator.house.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import xyz.bukinator.house.model.House
import java.util.UUID

interface HouseRepository : JpaRepository<House, UUID>, JpaSpecificationExecutor<House>
