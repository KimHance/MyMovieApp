package com.navermovie.data.remote.datasource.impl

import android.util.Log
import com.navermovie.data.remote.datasource.KmdbSearchDataSource
import com.navermovie.data.remote.response.Tmp
import com.navermovie.data.remote.service.KmdbSearchService
import javax.inject.Inject

class KmdbSearchDataSourceImpl @Inject constructor(
    private val movieService: KmdbSearchService
) : KmdbSearchDataSource {
    override suspend fun getMoviePlot(title: String): Tmp? {
        return runCatching {
            movieService.getMoviePlot(title)
        }.mapCatching {
            Log.d("과연", "${it.code()} : ${it.body()}")
            it.body()
        }.getOrNull()
    }
}