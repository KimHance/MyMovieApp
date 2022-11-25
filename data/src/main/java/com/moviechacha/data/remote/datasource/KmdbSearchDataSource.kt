package com.moviechacha.data.remote.datasource

import com.moviechacha.data.remote.response.KmdbResponse
import kotlinx.coroutines.flow.Flow

interface KmdbSearchDataSource {

    suspend fun getMoviePlot(title: String): Flow<KmdbResponse?>
}