package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.NaverMovieDataSource
import com.navermovie.data.remote.response.PosterResult
import com.navermovie.data.remote.service.NaverMovieService
import javax.inject.Inject

class NaverMovieDataSourceImpl @Inject constructor(
    private val movieService: NaverMovieService
) : NaverMovieDataSource {

    override suspend fun getMoviePoster(query: String): PosterResult =
        run { movieService.getMoviePoster(query) }
}