package xyz.bukinator.common.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(
    basePackages = ["xyz.bukinator.house.repository"]
)
@EntityScan("xyz.bukinator.house.model")
class HouseDatabaseConfiguration
