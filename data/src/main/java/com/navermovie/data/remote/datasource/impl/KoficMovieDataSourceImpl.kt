package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.response.BoxOffice
import com.navermovie.data.remote.response.SearchResponse
import com.navermovie.data.remote.service.KoficMovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KoficMovieDataSourceImpl @Inject constructor(
    private val movieService: KoficMovieService
) : KoficMovieDataSource {

    override fun getDailyBoxOfficeList(): Flow<List<BoxOffice>?> = flow {
        emit(movieService.getDailyBoxOffice().boxOfficeResult?.dailyBoxOfficeList)
    }

    override fun getWeeklyBoxOfficeList(): Flow<List<BoxOffice>?> = flow {
        emit(movieService.getWeeklyBoxOffice().boxOfficeResult?.weeklyBoxOfficeList)
    }

    override fun getMovieDetail(movieCd: String) = flow {
        emit(movieService.getMovieDetail(movieCd))
    }

    override suspend fun getSearchResponse(query: String): SearchResponse =
        movieService.getSearchList(query)
}