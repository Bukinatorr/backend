package xyz.bukinator.batch.zigbang.oneroom

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import xyz.bukinator.batch.geohash.GeoHashService

@Service
class OneRoomItemFetcher(
    private val geoHashService: GeoHashService
) {
    private val baseUrl = "https://apis.zigbang.com"
    private val geoHash = geoHashService.getGeoHash() // 임시값임
    private val client = WebClient
        .builder()
        .exchangeStrategies(
            ExchangeStrategies
                .builder()
                .codecs {
                    run {
                        it.defaultCodecs().maxInMemorySize(-1)
                    }
                }
                .build()
        )
        .baseUrl(baseUrl)
        .build()

    fun getItemIds() : List<Long>? {
        return fetchItemIds()?.items?.map { it.itemId }
    }

    fun getItemList(itemIds: List<Long>) : List<OneRoomItemList>? {
        return fetchItemList(itemIds)?.items
    }

    // 스프링6부터 RestTemplate이 deprecated된다고 해서 일단 webflux block으로 함...
    private fun fetchItemIds() : OneRoomItemIdResponse? {
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
                .bodyToMono(OneRoomItemIdResponse::class.java)
                .block()
        } catch (e: Exception) {
            null
        }
    }

    private fun fetchItemList(itemIds: List<Long>) : OneRoomItemListResponse? {
        return try {
            client
                .post()
                .uri("/v2/items/list")
                .bodyValue(mapOf(
                    "domain" to "zigbang",
                    "item_ids" to itemIds,
                ))
                .retrieve()
                .bodyToMono(OneRoomItemListResponse::class.java)
                .block()
        } catch (e: Exception) {
            null
        }
    }
}