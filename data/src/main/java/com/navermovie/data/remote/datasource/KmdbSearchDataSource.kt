package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KmdbResponse
import com.navermovie.data.remote.response.Tmp

interface KmdbSearchDataSource {

    suspend fun getMoviePlot(title: String): Tmp?
}