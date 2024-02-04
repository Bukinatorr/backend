package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

internal open class ZigbangDataFetcher {
    private val baseUrl = "https://apis.zigbang.com"
    private val objectMapper = ObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
    private val client = WebClient.builder()
        .exchangeStrategies(
            ExchangeStrategies
                .builder()
                .codecs {configurer ->
                    run {
                        configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
                        configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
                        configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
                    }
                }
                .build()
        )
        .baseUrl(baseUrl)
        .build()

    fun fetchOneroomItemIds(geoHash: String): ZigbangOneroomItemIdResponse? {
        return client
            .get()
            .uri {
                it.path("/v2/items/oneroom")
                    .queryParam("domain", "zigbang")
                    .queryParam("geohash", geoHash)
                    .build()
            }
            .retrieve()
            .bodyToMono(ZigbangOneroomItemIdResponse::class.java)
            .block()
    }

    fun fetchOfficetelItemIds(geoHash: String): ZigbangOfficetelItemIdResponse? {
        return client
            .get()
            .uri {
                it.path("/v2/items/officetel")
                    .queryParam("domain", "zigbang")
                    .queryParam("geohash", geoHash)
                    .build()
            }
            .retrieve()
            .bodyToMono(ZigbangOfficetelItemIdResponse::class.java)
            .block()
    }

    fun fetchOneroomItemList(itemIds: List<Long>): ZigbangItemSummaryResponse? {
        return client
            .post()
            .uri("/v2/items/list")
            .bodyValue(
                mapOf(
                    "domain" to "zigbang",
                    "item_ids" to itemIds
                )
            )
            .retrieve()
            .bodyToMono(ZigbangItemSummaryResponse::class.java)
            .block()
    }

    fun fetchOfficetelItemList(itemIds: List<Long>): ZigbangItemSummaryResponse? {
        return client
            .post()
            .uri("/v2/items/list")
            .bodyValue(
                mapOf(
                    "domain" to "zigbang",
                    "item_ids" to itemIds
                )
            )
            .retrieve()
            .bodyToMono(ZigbangItemSummaryResponse::class.java)
            .block()
    }

    fun fetchOneroomItemDetail(itemId: Long): JsonNode? {
        return client
            .get()
            .uri {
                it.path("/v3/items/$itemId")
                    .queryParam("domain", "zigbang")
                    .build()
            }
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .block()
    }

    fun fetchOfficetelItemDetail(itemId: Long): JsonNode? {
        return client
            .get()
            .uri {
                it.path("/v3/items/$itemId")
                    .queryParam("domain", "zigbang")
                    .build()
            }
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .block()
    }
}
