package com.navermovie.data.remote.service

import com.navermovie.KMDB_KEY
import com.navermovie.data.remote.response.Tmp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KmdbSearchService {

    @GET("search_json2.jsp?collection=kmdb_new2")
    suspend fun getMoviePlot(
        @Query("query") query: String,
        @Query("ServiceKey") key: String = KMDB_KEY
    ): Response<Tmp>
}