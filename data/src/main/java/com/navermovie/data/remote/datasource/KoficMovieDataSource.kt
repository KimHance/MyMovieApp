package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.BoxOffice
import com.navermovie.data.remote.response.KoficMovieInfoResult
import com.navermovie.data.remote.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface KoficMovieDataSource {

    fun getDailyBoxOfficeList(): Flow<List<BoxOffice>?>

    fun getWeeklyBoxOfficeList(): Flow<List<BoxOffice>?>

    fun getMovieDetail(movieCd: String): Flow<KoficMovieInfoResult?>

    suspend fun getSearchResponse(query: String): SearchResponse
}