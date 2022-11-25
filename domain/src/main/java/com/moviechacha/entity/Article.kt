package com.moviechacha.entity

data class Article(
    val link: String? = "",
    val title: String? = "",
    val description: String? = "",
    val isFetched: Boolean = false
)