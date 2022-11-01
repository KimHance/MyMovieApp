package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.NaverSearchDataSource
import com.navermovie.data.remote.service.NaverSearchService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NaverSearchDataSourceImpl @Inject constructor(
    private val searchService: NaverSearchService
) : NaverSearchDataSource {

    override suspend fun getMoviePoster(query: String) = flow {
        emit(
            run { searchService.getMoviePoster(query) }
        )
    }

    override suspend fun getMovieArticle(query: String) = flow {
        emit(
            run { searchService.getMovieArticle(query) }
        )
    }
}