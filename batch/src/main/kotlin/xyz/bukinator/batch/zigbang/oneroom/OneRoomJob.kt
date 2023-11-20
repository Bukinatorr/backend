package xyz.bukinator.batch.zigbang.oneroom

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import xyz.bukinator.batch.bukinator.House

@Configuration
class OneRoomJob(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    private val oneRoomItemReader: OneRoomItemReader,
    private val oneRoomItemProcessor: OneRoomItemProcessor,
    private val oneRoomItemWriter: OneRoomItemWriter
) {
    @Bean
    fun oneRoomUpdateJob() : Job {
        return JobBuilder("oneRoomUpdateJob11", jobRepository)
            .listener(jobListener())
            .start(oneRoomUpdateStep())
            .build()
    }

    fun jobListener(): JobExecutionListener {
        return object : JobExecutionListener {
            override fun beforeJob(jobExecution: JobExecution) {
                oneRoomItemReader.idsInit()
            }

            override fun afterJob(jobExecution: JobExecution) {
                println("Job finished with status: ${jobExecution.status}")
            }
        }
    }

    fun oneRoomUpdateStep() : Step {
        return StepBuilder("oneRoomUpdateStep", jobRepository)
            .chunk<List<OneRoomItemList>, List<House>>(100, transactionManager)
            .reader(oneRoomItemReader)
            .processor(oneRoomItemProcessor)
            .writer(oneRoomItemWriter)
            .build()
    }
}