package com.navermovie.entity

data class Movie(
    val title: String = "",
    val rank: Int = 0,
    val rankType: String? = "",
    val movieCd: String = "",
    val openDate: String? = "",
    val actors: List<String>? = emptyList(),
    val genres: List<String>? = emptyList(),
    val audits: List<String>? = emptyList(),
    val directors: List<String>? = emptyList(),
    val showTime: String? = "",
    val poster: String? = "",
    val rating: String? = ""
)
