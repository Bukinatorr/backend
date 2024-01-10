package xyz.bukinator.batch.core

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.*
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import xyz.bukinator.client.domain.model.ExternalDataSummary
import xyz.bukinator.house.domain.House
import xyz.bukinator.house.dto.CreateHouseDto
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class OriginHouseDataCreateConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    private val originHouseDataReader: OriginHouseDataReader,
) {

    @Bean
    fun originHouseDateUpdateJob(): Job {
        return JobBuilder("now()", jobRepository)
            .listener(jobListener())
            .start(oneRoomUpdateStep())
            .build()
    }

    @Bean
    @JobScope
    fun oneRoomUpdateStep(): Step {
        return StepBuilder("oneRoomUpdateStep", jobRepository)
            .chunk<ExternalDataSummary, List<CreateHouseDto>>(100, transactionManager)
            .reader(originHouseDataReader)
            .processor(originHouseDataProcessor())
            .writer(oneRoomItemWriter())
            .build()
    }

    @Bean
    @StepScope
    fun originHouseDataProcessor(): ItemProcessor<ExternalDataSummary, List<CreateHouseDto>> {
        return ItemProcessor { summaries ->
            summaries.data.map {
                val addressOriginJson = it.getAsJsonObject("address_origin")

                CreateHouseDto(
                    originSource = it["origin_source"].asString,
                    originId = it["item_id"].asString,
                    originUpdatedAt = it["origin_updated_at"].asString.toLocalDateTime(),
                    salesType = it["sales_type"].asString,
                    houseName = it["house_name"]?.asString ?: "",
                    houseType = it["house_type"]?.asString ?: "",
                    roomType = it["room_type"]?.asString ?: "",
                    roomDirection = it["room_direction"]?.asString ?: "",
                    thumbnail = it["thumbnail"]?.asString ?: "",
                    images = if (it.has("images") && !it.get("images").isJsonNull) {
                        it.get("images").asJsonArray.map { jsonElement -> jsonElement.asString }
                    } else {
                        emptyList()
                    },
                    priceDeposit = it["price_deposit"]?.asInt ?: 0,
                    priceRent = it["price_rent"]?.asInt ?: 0,
                    priceManage = it["price_manage"]?.asInt ?: 0,
                    priceManageIncludes = if (it.has("price_manage_includes") && !it.get("price_manage_includes").isJsonNull) {
                        it.get("price_manage_includes").asJsonArray.map { jsonElement -> jsonElement.asString }
                    } else {
                        emptyList()
                    },
                    areaContract = it["area_contract"]?.asDouble ?: 0.0,
                    areaSupply = it["area_supply"]?.asDouble ?: 0.0,
                    areaIndividual = it["area_individual"]?.asDouble ?: 0.0,
                    title = it["title"]?.asString ?: "",
                    description = it["description"]?.asString ?: "",
                    status = if (it["status"]?.asBoolean == true) "OPEN" else "CLOSED",
                    lat = if (it.has("random_location") && !it.get("random_location").isJsonNull) {
                        it.get("random_location").asJsonObject.get("lat").asDouble
                    } else {
                        0.0
                    },
                    lng = if (it.has("random_location") && !it.get("random_location").isJsonNull) {
                        it.get("random_location").asJsonObject.get("lng").asDouble
                    } else {
                        0.0
                    },
                    parkingCount = it["parking_count"]?.asInt ?: 0,
                    elevator = it["elevator"]?.asBoolean ?: false,
                    movinDate = if (it.has("movin_date") && !it.get("movin_date").isJsonNull) {
                        it.get("movin_date").asString.toLocalDate()
                    } else {
                        null
                    },
                    approveDate = if (it.has("approve_date") && !it.get("approve_date").isJsonNull) {
                        it.get("approve_date").asString.toLocalDate()
                    } else {
                        null
                    },
                    residenceType = it["residence_type"]?.asString ?: "",
                    pnu = it["pnu"]?.asString ?: "",
                    floorTotal = it["floor_total"]?.asInt ?: 0,
                    floorTarget = it["floor_target"]?.asInt ?: 0,
                    options = if (it.has("options") && !it.get("options").isJsonNull) {
                        it.get("options").asJsonArray.map { jsonElement -> jsonElement.asString }
                    } else {
                        emptyList()
                    },
                    addressLocal1 = addressOriginJson?.get("local1")?.asString ?: "",
                    addressLocal2 = addressOriginJson?.get("local2")?.asString ?: "",
                    addressLocal3 = addressOriginJson?.get("local3")?.asString ?: "",
                    addressLocal4 = addressOriginJson?.get("local4")?.asString ?: "",
                    addressJibun = addressOriginJson?.get("fullText")?.asString ?: ""
                )
            }
        }
    }

    fun jobListener(): JobExecutionListener {
        return object : JobExecutionListener {
            override fun beforeJob(jobExecution: JobExecution) {
                println("before job")
            }

            override fun afterJob(jobExecution: JobExecution) {
                println("after job")
            }
        }
    }

    @Bean
    @StepScope
    fun oneRoomItemWriter(): ItemWriter<List<CreateHouseDto>> {
        return ItemWriter { CreateHouseDtoList ->
            CreateHouseDtoList.flatten().forEach { createHouseDto ->
                println(createHouseDto)
                House.create(createHouseDto)
            }
        }
    }

    fun String.toLocalDateTime(): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(this, formatter)
    }

    fun String.toLocalDate(): LocalDate {
        return LocalDate.parse(this)
    }
}
