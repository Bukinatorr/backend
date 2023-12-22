package xyz.bukinator.batch.core

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.*
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OriginHouseDataCreateConfig(
    val houseService: HouseService,
    val jobBuilder: JobBuilder,
    val stepBuilder: StepBuilder,
) {
    @Bean(name = ["OriginHouseDataCreateJob"])
    @Throws(Exception::class)
    fun OriginHouseDataCreateJob(
        jobBuilderFactory: JobBuilderFactory,
    ): Job {
        return jobBuilderFactory.get("OriginHouseDataCreateJob")
            .incrementer(RunIdIncrementer())
            .start(OriginHouseDataCreateStep())
            .build()
    }

    @Bean
    @JobScope
    @Throws(Exception::class)
    fun OriginHouseDataCreateStep(): Step {
        return stepBuilderFactory.get("OriginHouseDataCreateStep")
            .chunk<OrginHouseRawData, CreateHouseDto>(100)
            .reader(originHouseDataReader)
            .processor(OriginHouseDataProcessor())
            .writer(OriginHouseDataWriter())
//            .listener() create 과정 중 중복 데이터 발생 listener 추가 필요 -> update로 연계
            .build()
    }

    @Bean
    @StepScope
    fun OriginHouseDataProcessor(): ItemProcessor<OrginHouseRawData, CreateHouseDto> {
        return ItemProcessor { rawData ->
            return CreateHouseDto(
                originSource = rawData["originSource"],
                originId = rawData["originId"],
                originUpdatedAt = rawData["originUpdatedAt"],
                salesType = rawData["salesType"],
                houseName = rawData["houseName"],
                houseType = rawData["houseType"],
                roomType = rawData["roomType"],
                roomDirection = rawData["roomDirection"],
                thumbnail = rawData["thumbnail"],
                images = rawData["images"],
                priceDeposit = rawData["priceDeposit"],
                priceRent = rawData["priceRent"],
                priceManage = rawData["priceManage"],
                priceManageIncludes = rawData["priceManageIncludes"],
                areaContract = rawData["areaContract"],
                areaSupply = rawData["areaSupply"],
                areaIndividual = rawData["areaIndividual"],
                title = rawData["title"],
                description = rawData["description"],
                status = rawData["status"],
                lat = rawData["lat"],
                lng = rawData["lng"],
                parkingCount = rawData["parkingCount"],
                elevator = rawData["elevator"],
                movinDate = rawData["movinDate"],
                approveDate = rawData["approveDate"],
                residenceType = rawData["residenceType"],
                pnu = rawData["pnu"],
                floorTotal = rawData["floorTotal"],
                floorTarget = rawData["floorTarget"],
                options = rawData["options"],
                addressLocal1 = rawData["addressLocal1"],
                addressLocal2 = rawData["addressLocal2"],
                addressLocal3 = rawData["addressLocal3"],
                addressLocal4 = rawData["addressLocal4"],
                addressJibun = rawData["addressJibun"],
            )
        }
    }

    @Bean
    @StepScope
    fun OriginHouseDataWriter(): ItemWriter<CreateHouseDto> {
        return ItemWriter { createHousesDto ->
            createHousesDto.forEach { createHouseDto ->
                houseService.createHouse(createHouseDto)
            }
        }
    }
}
