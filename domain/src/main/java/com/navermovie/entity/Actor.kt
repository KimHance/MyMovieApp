package com.navermovie.entity

data class Actor(
    val name: String = "",
    val imageUrl: String? = "",
    val isFetched: Boolean = false
)