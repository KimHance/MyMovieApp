package com.moviechacha.data.remote.service

import com.moviechacha.KMDB_KEY
import com.moviechacha.data.remote.response.KmdbResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KmdbSearchService {

    @GET("search_json2.jsp?collection=kmdb_new2")
    suspend fun getMoviePlot(
        @Query("query") query: String,
        @Query("ServiceKey") key: String = KMDB_KEY
    ): KmdbResponse
}