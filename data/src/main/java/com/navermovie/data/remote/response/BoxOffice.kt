package com.navermovie.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class KoficBoxOfficeResult(
    val boxOfficeResult: BoxOfficeResult?
)

@Serializable
data class BoxOfficeResult(
    val dailyBoxOfficeList: List<DailyBoxOffice>?
)

@Serializable
data class DailyBoxOffice(
    val rank: String,
    val rankOldAndNew: String,
    val movieNm: String,
    val movieCd: String
)
