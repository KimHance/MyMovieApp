package com.moviechacha.data.remote.datasource.impl

import com.moviechacha.data.remote.datasource.NaverSearchDataSource
import com.moviechacha.data.remote.service.NaverSearchService
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