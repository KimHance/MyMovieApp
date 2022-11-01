package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KoficBoxOfficeResult
import com.navermovie.data.remote.response.KoficMovieInfoResult
import kotlinx.coroutines.flow.Flow

interface KoficMovieDataSource {

    suspend fun getDailyBoxOfficeList(): Flow<KoficBoxOfficeResult?>

    suspend fun getMovieDetail(movieCd: String): Flow<KoficMovieInfoResult?>
}