package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.NaverSearchDataSource
import com.navermovie.data.remote.response.NaverArticleResponse
import com.navermovie.data.remote.response.PosterResult
import com.navermovie.data.remote.service.NaverSearchService
import javax.inject.Inject

class NaverSearchDataSourceImpl @Inject constructor(
    private val searchService: NaverSearchService
) : NaverSearchDataSource {

    override suspend fun getMoviePoster(query: String): PosterResult =
        run { searchService.getMoviePoster(query) }

    override suspend fun getMovieArticle(query: String): NaverArticleResponse =
        run { searchService.getMovieArticle(query) }
}