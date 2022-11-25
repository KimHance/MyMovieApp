package com.moviechacha.data.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class YoutubeResponse(
    val items: List<Item?>?,
) {
    @Serializable
    data class Item(
        val id: Id?,
    ) {
        @Serializable
        data class Id(
            val videoId: String? // 9qQuoqpw7KA
        )
    }
}