package com.navermovie.data.remote.service

import com.navermovie.KOFIC_KEY
import com.navermovie.data.remote.response.KoficBoxOfficeResult
import com.navermovie.data.remote.response.KoficMovieInfoResult
import com.navermovie.data.remote.response.SearchResponse
import com.navermovie.getCurrentDate
import com.navermovie.getLastWeek
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
        @Query("movieNm") query: String,
        @Query("key") key: String = KOFIC_KEY,
        @Query("curPage") page: Int = 1,
        @Query("itemPerPage") itemPerPage: String = "20"
    ): SearchResponse
}