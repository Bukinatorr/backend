package xyz.bukinator.house.domain
import org.springframework.data.jpa.repository.JpaRepository

interface HouseRepository : JpaRepository<House, Long>
