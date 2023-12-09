package xyz.bukinator.house.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.bukinator.house.model.House
import java.util.UUID

interface HouseRepository : JpaRepository<House, UUID>
