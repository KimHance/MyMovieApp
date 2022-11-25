package com.moviechacha.data.remote.datasource.impl

import com.moviechacha.data.remote.datasource.KoficMovieDataSource
import com.moviechacha.data.remote.response.BoxOffice
import com.moviechacha.data.remote.service.KoficMovieService
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

    override fun getSearchResponse(query: String) = flow {
        emit(movieService.getSearchList(query = query))
    }

}