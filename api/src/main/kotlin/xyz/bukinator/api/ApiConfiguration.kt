package xyz.bukinator.api

import org.springframework.context.annotation.Import
import xyz.bukinator.common.configuration.HouseDatabaseConfiguration

@Import(HouseDatabaseConfiguration::class)
class ApiConfiguration
