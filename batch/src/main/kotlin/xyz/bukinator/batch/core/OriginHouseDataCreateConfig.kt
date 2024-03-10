package xyz.bukinator.batch.core

import com.fasterxml.jackson.databind.JsonNode
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
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.PlatformTransactionManager
import xyz.bukinator.client.domain.external.ExternalDataSummary
import xyz.bukinator.house.dto.HouseDto
import xyz.bukinator.house.service.HouseService
import java.util.*

@Configuration
@EnableJpaRepositories(
    basePackages = ["xyz.bukinator.house.repository"]
)
@EntityScan("xyz.bukinator.house.model")
class OriginHouseDataCreateConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    private val originHouseDataReader: OriginHouseDataReader,
    private val houseService: HouseService,
) {

    @Bean
    fun originHouseDateUpdateJob(): Job {
        return JobBuilder("${UUID.randomUUID()}", jobRepository)
            .listener(jobListener())
            .start(oneRoomUpdateStep())
            .build()
    }

    @Bean
    @JobScope
    fun oneRoomUpdateStep(): Step {
        return StepBuilder("oneRoomUpdateStep", jobRepository)
            .chunk<List<ExternalDataSummary>, List<HouseDto>>(STEP_BUILDER_CHUNK_SIZE, transactionManager)
            .reader(originHouseDataReader)
            .processor(originHouseDataProcessor())
            .writer(oneRoomItemWriter())
            .build()
    }

    @Bean
    @StepScope
    fun originHouseDataProcessor(): ItemProcessor<List<ExternalDataSummary>, List<HouseDto>> {
        return ItemProcessor { externalDataSummaries ->
            externalDataSummaries.map {
                HouseDto.of(it)
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
    fun oneRoomItemWriter(): ItemWriter<List<HouseDto>> {
        return ItemWriter { houseDtoList ->
            houseService.createOrUpdateAll(houseDtoList.flatten())
        }
    }

    companion object {
        const val STEP_BUILDER_CHUNK_SIZE = 1
    }
}
