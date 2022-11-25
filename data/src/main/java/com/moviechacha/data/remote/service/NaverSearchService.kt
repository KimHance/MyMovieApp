package com.moviechacha.data.remote.service

import com.moviechacha.data.remote.response.NaverArticleResponse
import com.moviechacha.data.remote.response.PosterResult
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverSearchService {

    @GET("movie.json")
    suspend fun getMoviePoster(
        @Query("query") query: String,
        @Query("display") display: Int = 20
    ): PosterResult

    @GET("news.json")
    suspend fun getMovieArticle(
        @Query("query") query: String,
        @Query("display") display: Int = 10
    ): NaverArticleResponse
}