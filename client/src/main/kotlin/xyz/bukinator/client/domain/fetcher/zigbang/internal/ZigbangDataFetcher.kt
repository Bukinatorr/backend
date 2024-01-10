package xyz.bukinator.client.domain.fetcher.zigbang.internal

import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

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

    fun fetchOneroomItemIds(geoHash: String) : OneroomItemIdResponse? {
        return try {
            client
                .get()
                .uri {
                    it.path("/v2/items/oneroom")
                        .queryParam("domain", "zigbang")
                        .queryParam("checkAnyItemWithoutFilter", true)
                        .queryParam("geohash", geoHash)
                        .build()
                }
                .retrieve()
                .bodyToMono(OneroomItemIdResponse::class.java)
                .block()
        } catch (e: Exception) {
            null
        }
    }

    fun fetchOfficetelItemIds(geoHash: String) : OfficetelItemIdResponse? {
        return try {
            client
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
                .bodyToMono(OfficetelItemIdResponse::class.java)
                .block()
        } catch (e: Exception) {
            null
        }
    }

    fun fetchItemList(itemIds: List<Long>) : ItemListResponse? {
        return try {
            client
                .post()
                .uri("/v2/items/list")
                .bodyValue(mapOf(
                    "domain" to "zigbang",
                    "item_ids" to itemIds,
                ))
                .retrieve()
                .bodyToMono(ItemListResponse::class.java)
                .block()
        } catch (e: Exception) {
            null
        }
    }

    fun fetchItemDetail(itemId: Long) : ItemDetailResponse? {
        return try {
            client
                .get()
                .uri {
                    it.path("/v3/items/$itemId")
                        .queryParam("domain", "zigbang")
                        .build()
                }
                .retrieve()
                .bodyToMono(ItemDetailResponse::class.java)
                .block()
        } catch (e: Exception) {
            null
        }
    }
}