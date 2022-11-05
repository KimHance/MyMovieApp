package com.navermovie.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class KoficBoxOfficeResult(
    val boxOfficeResult: BoxOfficeResult?
)

@Serializable
data class BoxOfficeResult(
    val weeklyBoxOfficeList: List<BoxOffice>?,
    val dailyBoxOfficeList: List<BoxOffice>?
)

@Serializable
data class BoxOffice(
    val rank: String,
    val rankOldAndNew: String,
    val movieNm: String,
    val movieCd: String
)
