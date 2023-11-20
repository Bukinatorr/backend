package xyz.bukinator.batch.bukinator

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HouseRepository : JpaRepository<House, Long>