package xyz.bukinator.domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

interface HouseRepository : JpaRepository<House, Long>