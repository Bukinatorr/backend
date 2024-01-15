package xyz.bukinator.client.domain.fetcher.zigbang.internal

import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import xyz.bukinator.client.domain.model.ExternalDataDetail
import xyz.bukinator.client.domain.model.ExternalDataSummary

internal open class ZigbangDataFetcher {
    private val baseUrl = "https://apis.zigbang.com"
    private val client = WebClient
        .builder()
        .exchangeStrategies(
            ExchangeStrategies
                .builder()
                .codecs {
                    run {
                        it.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
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
                    .queryParam("checkAnyItemWithoutFilter", true)
                    .queryParam("withBuildings", true)
                    .queryParam("geohash", geoHash)
                    .build()
            }
            .retrieve()
            .bodyToMono(ZigbangOfficetelItemIdResponse::class.java)
            .block()
    }

    fun fetchOneroomItemList(itemIds: List<Long>): ExternalDataSummary? {
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
            .bodyToMono(ZigbangOneroomItemListResponse::class.java)
            .block()
    }

    fun fetchOfficetelItemList(itemIds: List<Long>): ExternalDataSummary? {
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
            .bodyToMono(ZigbangOfficetelItemListResponse::class.java)
            .block()
    }

    fun fetchOneroomItemDetail(itemId: Long): ExternalDataDetail? {
        return client
            .get()
            .uri {
                it.path("/v3/items/$itemId")
                    .queryParam("domain", "zigbang")
                    .build()
            }
            .retrieve()
            .bodyToMono(ZigbangOnreoomItemDetailResponse::class.java)
            .block()
    }

    fun fetchOfficetelItemDetail(itemId: Long): ExternalDataDetail? {
        return client
            .get()
            .uri {
                it.path("/v3/items/$itemId")
                    .queryParam("domain", "zigbang")
                    .build()
            }
            .retrieve()
            .bodyToMono(ZigbangOfficetelItemDetailResponse::class.java)
            .block()
    }
}
