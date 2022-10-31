package com.navermovie.data.remote.datasource.impl

import android.util.Log
import com.navermovie.data.remote.datasource.KmdbSearchDataSource
import com.navermovie.data.remote.response.KmdbResponse
import com.navermovie.data.remote.service.KmdbSearchService
import javax.inject.Inject

class KmdbSearchDataSourceImpl @Inject constructor(
    private val movieService: KmdbSearchService
) : KmdbSearchDataSource {
    override suspend fun getMoviePlot(title: String): KmdbResponse? {
        return runCatching {
            movieService.getMoviePlot(title)
        }.mapCatching {
            it.body()
        }.getOrNull()
    }
}