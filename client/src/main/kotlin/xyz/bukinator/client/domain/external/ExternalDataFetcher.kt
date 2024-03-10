package xyz.bukinator.client.domain.external

interface ExternalDataFetcher {
    fun fetchItemIds(geohash: String): List<Long>
    fun fetchItemIds(lat: Long, lng: Long): List<Long>
    fun fetchItemSummaries(itemIds: List<Long>): List<ExternalDataSummary>
    fun fetchItemDetail(itemId: Long): ExternalDataDetail
}
