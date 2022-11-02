package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KmdbResponse
import kotlinx.coroutines.flow.Flow

interface KmdbSearchDataSource {

    suspend fun getMoviePlot(title: String): Flow<KmdbResponse?>
}