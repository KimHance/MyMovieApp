package com.navermovie.data.remote.service

import com.navermovie.KOFIC_KEY
import com.navermovie.data.remote.response.KoficBoxOfficeResult
import com.navermovie.data.remote.response.KoficMovieInfoResult
import com.navermovie.getCurrentDate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KoficMovieService {

    @GET("boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getDailyBoxOffice(
        @Query("key") key: String = KOFIC_KEY,
        @Query("targetDt") date: String = getCurrentDate()
    ): KoficBoxOfficeResult

    @GET("movie/searchMovieInfo.json")
    suspend fun getMovieDetail(
        @Query("movieCd") movieCd: String,
        @Query("key") key: String = KOFIC_KEY
    ): KoficMovieInfoResult
}