package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.BoxOffice
import com.navermovie.data.remote.response.KoficMovieInfoResult
import kotlinx.coroutines.flow.Flow

interface KoficMovieDataSource {

    suspend fun getDailyBoxOfficeList(): Flow<List<BoxOffice>?>

    suspend fun getWeeklyBoxOfficeList(): Flow<List<BoxOffice>?>

    suspend fun getMovieDetail(movieCd: String): Flow<KoficMovieInfoResult?>
}