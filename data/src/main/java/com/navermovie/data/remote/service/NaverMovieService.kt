package com.navermovie.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieService {

    @GET
    suspend fun getMoviePoster(
        @Query("query") query: String
    )
}