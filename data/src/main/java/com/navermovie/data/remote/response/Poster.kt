package com.navermovie.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PosterResult(
    val items: List<Item?>
)

@Serializable
data class Item(
    val image: String,
    val userRating: String,
    val director: String,
    val pubDate: String
)