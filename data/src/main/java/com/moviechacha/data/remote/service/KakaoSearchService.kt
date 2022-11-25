package com.moviechacha.data.remote.service

import com.moviechacha.data.remote.response.KakaoImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoSearchService {

    @GET("v2/search/image")
    suspend fun getActorImage(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("size") sizer: Int = 1
    ): KakaoImageResponse
}