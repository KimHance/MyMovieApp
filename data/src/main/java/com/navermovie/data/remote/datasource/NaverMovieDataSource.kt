package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.PosterResult
import retrofit2.Response

interface NaverMovieDataSource {

    suspend fun getMoviePoster(query: String): PosterResult
}