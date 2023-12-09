package xyz.bukinator.house.model
import org.springframework.data.jpa.repository.JpaRepository

interface HouseRepository : JpaRepository<House, Long>
