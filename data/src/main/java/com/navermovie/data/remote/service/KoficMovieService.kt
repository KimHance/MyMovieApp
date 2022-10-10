package com.navermovie.data.remote.service

import com.navermovie.KOFIC_KEY
import com.navermovie.getCurrentDate
import retrofit2.http.GET
import retrofit2.http.Query

interface KoficMovieService {

    // Weekly
    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    suspend fun getWeeklyBoxOffice(
        @Query("key") key: String = KOFIC_KEY,
        @Query("targetDt") date: String = getCurrentDate()
    )

    // Daily
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getDailyBoxOffice(
        @Query("key") key: String = KOFIC_KEY,
        @Query("targetDt") date: String = getCurrentDate()
    )
}