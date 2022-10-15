package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.response.KoficBoxOfficeResult
import com.navermovie.data.remote.response.KoficMovieInfoResult
import com.navermovie.data.remote.service.KoficMovieService
import javax.inject.Inject

class KoficMovieDataSourceImpl @Inject constructor(
    private val movieService: KoficMovieService
) : KoficMovieDataSource {

    override suspend fun getDailyBoxOfficeList(): KoficBoxOfficeResult =
        run { movieService.getDailyBoxOffice() }

    override suspend fun getMovieDetail(movieCd: String): KoficMovieInfoResult =
        run { movieService.getMovieDetail(movieCd) }

}