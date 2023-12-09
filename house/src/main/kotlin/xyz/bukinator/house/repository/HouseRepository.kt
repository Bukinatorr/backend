package xyz.bukinator.house.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.bukinator.house.model.House

interface HouseRepository : JpaRepository<House, Long>
