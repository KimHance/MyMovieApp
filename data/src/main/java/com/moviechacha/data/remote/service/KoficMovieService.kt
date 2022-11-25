package com.moviechacha.data.remote.service

import com.moviechacha.KOFIC_KEY
import com.moviechacha.PAGE_SIZE
import com.moviechacha.data.remote.response.KoficBoxOfficeResult
import com.moviechacha.data.remote.response.KoficMovieInfoResult
import com.moviechacha.data.remote.response.SearchResponse
import com.moviechacha.getCurrentDate
import com.moviechacha.getLastWeek
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

    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    suspend fun getWeeklyBoxOffice(
        @Query("key") key: String = KOFIC_KEY,
        @Query("targetDt") date: String = getLastWeek(),
        @Query("weekGb") type: String = "0"
    ): KoficBoxOfficeResult

    @GET("movie/searchMovieList.json")
    suspend fun getSearchList(
        @Query("movieNm") query: String = "",
        @Query("itemPerPage") size: Int = PAGE_SIZE,
        @Query("key") key: String = KOFIC_KEY,
    ): SearchResponse
}