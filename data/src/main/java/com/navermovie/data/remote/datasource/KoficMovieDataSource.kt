package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KoficBoxOfficeResult
import com.navermovie.data.remote.response.KoficMovieInfoResult

interface KoficMovieDataSource {

    suspend fun getDailyBoxOfficeList(): KoficBoxOfficeResult

    suspend fun getMovieDetail(movieCd: String): KoficMovieInfoResult
}