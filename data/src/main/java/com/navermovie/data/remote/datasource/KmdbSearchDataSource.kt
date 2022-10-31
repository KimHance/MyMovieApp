package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KmdbResponse

interface KmdbSearchDataSource {

    suspend fun getMoviePlot(title: String): KmdbResponse?
}