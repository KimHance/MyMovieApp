package com.navermovie.entity

import java.io.Serializable

data class Movie(
    val title: String = "",
    val rank: Int = 0,
    val rankType: String? = "",
    val movieCd: String = "",
    val openDate: String? = "",
    val prodYear: String? = "",
    val actors: List<String>? = emptyList(),
    val genres: List<String>? = emptyList(),
    val audits: List<String>? = emptyList(),
    val directors: List<Directors> = emptyList(),
    val showTime: String? = "",
    val poster: String? = "",
    val rating: String? = "",
    val isFetched: Boolean = false,
    val isError: Boolean = false,
) : Serializable

data class Directors(
    val name: String? = "",
    val englishName: String = ""
) : Serializable