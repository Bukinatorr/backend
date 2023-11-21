package xyz.bukinator.api.domain.house

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

interface HouseRepository : JpaRepository<House, Long>