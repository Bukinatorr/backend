package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

internal open class DataFetcher {
    private val baseUrl = "https://apis.zigbang.com"
    private val objectMapper = ObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
    private val client = WebClient.builder()
        .exchangeStrategies(
            ExchangeStrategies
                .builder()
                .codecs { configurer ->
                    run {
                        configurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024)
                        configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
                        configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
                    }
                }
                .build()
        )
        .baseUrl(baseUrl)
        .build()

    fun fetchItemIds(serviceType: String, geoHash: String): ItemIdResponse {
        return client
            .get()
            .uri {
                it.path("/v2/items/$serviceType")
                    .queryParam("domain", "zigbang")
                    .queryParam("geohash", geoHash)
                    .build()
            }
            .retrieve()
            .bodyToMono(ItemIdResponse::class.java)
            .onErrorResume { e ->
                throw RuntimeException("Failed to fetch data", e)
            }
            .block() ?: throw RuntimeException("fetched data is null")
    }

    fun fetchItemList(itemIds: List<Long>): ItemSummaryResponse {
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
            .bodyToMono(ItemSummaryResponse::class.java)
            .onErrorResume { e ->
                throw RuntimeException("Failed to fetch data", e)
            }
            .block() ?: throw RuntimeException("fetched data is null")
    }

    fun fetchItemDetail(itemId: Long): ItemDetailResponse {
        return client
            .get()
            .uri {
                it.path("/v3/items/$itemId")
                    .queryParam("domain", "zigbang")
                    .build()
            }
            .retrieve()
            .bodyToMono(ItemDetailResponse::class.java)
            .onErrorResume { e ->
                throw RuntimeException("Failed to fetch data", e)
            }
            .block() ?: throw RuntimeException("fetched data is null")
    }
}
