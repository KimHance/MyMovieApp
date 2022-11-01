package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.service.KoficMovieService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KoficMovieDataSourceImpl @Inject constructor(
    private val movieService: KoficMovieService
) : KoficMovieDataSource {

    override suspend fun getDailyBoxOfficeList() = flow {
        emit(
            run { movieService.getDailyBoxOffice() }
        )
    }

    override suspend fun getMovieDetail(movieCd: String) = flow {
        emit(
            run { movieService.getMovieDetail(movieCd) }
        )
    }
}