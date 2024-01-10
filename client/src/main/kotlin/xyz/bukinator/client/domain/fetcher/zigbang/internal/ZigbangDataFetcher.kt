package xyz.bukinator.client.domain.fetcher.zigbang.internal

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
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
                        it.defaultCodecs().maxInMemorySize(-1)
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

//            OneroomItemIdResponse(listOf(
//                OneroomItemIdResponse.Item(
//                    lat = 37.50668595240607,
//                    lng = 127.04323158487844,
//                    itemId = 38969074,
//                    itemBmType = "ZIGBANG",
//                )
//            ))
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
//            client
//                .post()
//                .uri("/v2/items/list")
//                .bodyValue(mapOf(
//                    "domain" to "zigbang",
//                    "item_ids" to itemIds,
//                ))
//                .retrieve()
//                .bodyToMono(ItemListResponse::class.java)
//                .block()
            val item1 = JsonObject().apply {
                addProperty("origin_source", "ZIGBANG")
                addProperty("item_id", 38969074)
                addProperty("origin_updated_at", "2023-12-23 17:38:45")
                addProperty("section_type", null as String?)
                addProperty("images_thumbnail", "https://ic.zigbang.com/ic/items/38969074/1.jpg")
                addProperty("sales_type", "월세")
                addProperty("sales_title", "월세")
                addProperty("deposit", 1000)
                addProperty("rent", 130)
                addProperty("size_m2", 39.67)

                // 공급면적
                val supplyArea = JsonObject().apply {
                    addProperty("m2", 39.67)
                    addProperty("p", "12")
                }
                add("공급면적", supplyArea)

                // 전용면적
                val exclusiveArea = JsonObject().apply {
                    addProperty("m2", 39.67)
                    addProperty("p", "12")
                }
                add("전용면적", exclusiveArea)

                addProperty("계약면적", null as String?)
                addProperty("room_type_title", null as String?)
                addProperty("floor", "2")
                addProperty("floor_string", "2")
                addProperty("building_floor", "4")
                addProperty("title", "넓은 원룸원거실, 지정주차")
                addProperty("is_first_movein", null as String?)
                addProperty("room_type", "02")
                addProperty("status", true)

                // tags
                val tags = JsonArray().apply {
                    add("추천")
                }
                add("tags", tags)

                addProperty("service_type", "원룸")

                // random_location
                val randomLocation = JsonObject().apply {
                    addProperty("lat", 37.50668595240607)
                    addProperty("lng", 127.04323158487844)
                }
                add("random_location", randomLocation)

                addProperty("manage_cost", "15")
                addProperty("reg_date", "2023-12-07T17:38:45+09:00")
                addProperty("is_new", false)

                // addressOrigin
                val addressOrigin = JsonObject().apply {
                    addProperty("local1", "서울시")
                    addProperty("local2", "강남구")
                    addProperty("local3", "역삼동")
                    addProperty("local4", "")
                    addProperty("fullText", "서울시 강남구 역삼동")
                    addProperty("localText", "강남구 역삼동")
                }
                add("addressOrigin", addressOrigin)

                // action
                val action = JsonObject().apply {
                    addProperty("isRead", false)
                    addProperty("isInquired", false)
                    addProperty("isRewarded", false)
                    addProperty("isReported", false)
                    addProperty("isChecked", false)
                    addProperty("isZzim", false)
                }
                add("action", action)

                addProperty("contract", "")
                addProperty("address", "강남구 역삼동")
                addProperty("is_zzim", false)
                addProperty("address1", "서울시 강남구 역삼동")
                addProperty("address2", null as String?)
                addProperty("address3", null as String?)
                addProperty("item_bm_type", "ZIGBANG")
            }

            ItemListResponse(listOf(item1))
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
