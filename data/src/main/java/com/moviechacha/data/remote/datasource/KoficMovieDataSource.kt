package com.moviechacha.data.remote.datasource

import com.moviechacha.data.remote.response.BoxOffice
import com.moviechacha.data.remote.response.KoficMovieInfoResult
import com.moviechacha.data.remote.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface KoficMovieDataSource {

    fun getDailyBoxOfficeList(): Flow<List<BoxOffice>?>

    fun getWeeklyBoxOfficeList(): Flow<List<BoxOffice>?>

    fun getMovieDetail(movieCd: String): Flow<KoficMovieInfoResult?>

    fun getSearchResponse(query: String): Flow<SearchResponse>
}