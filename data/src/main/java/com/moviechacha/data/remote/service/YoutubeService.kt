package com.moviechacha.data.remote.service

import com.moviechacha.YOUTUBE_KEY
import com.moviechacha.data.remote.response.YoutubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("search")
    suspend fun getYoutubeLink(
        @Query("q") query: String,
        @Query("part") part: String = "snippet",
        @Query("maxResult") maxResult: Int = 1,
        @Query("type") type: String = "video",
        @Query("key") key: String = YOUTUBE_KEY,
    ): YoutubeResponse
}