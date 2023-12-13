package xyz.bukinator.client.domain.model

import com.google.gson.JsonObject

/*
    dataType의 경우 원룸, 오피스텔 등 데이터 소스 안에서 다른 데이터 타입을 구분하여
    사용하는 모듈의 편의성을 생각해서 넣어봤는데 필요 없으면 지워도 될듯
 */
data class ExternalDataSummary (
    val source: ExternalDataSource,
    val data: List<JsonObject>
)

class ExternalDataDetail (
    val source: ExternalDataSource,
    val data: JsonObject
)

enum class ExternalDataSource {
    ZIGBANG_ONEROOM, ZIGBANG_OFFICETEL, SOURCE_ELSE
}