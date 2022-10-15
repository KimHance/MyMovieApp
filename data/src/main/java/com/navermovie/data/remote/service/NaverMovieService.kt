package com.navermovie.data.remote.service

import com.navermovie.data.remote.response.PosterResult
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieService {

    @GET("movie.json")
    suspend fun getMoviePoster(
        @Query("query") query: String,
        @Query("display") display: Int = 1
    ): PosterResult
}