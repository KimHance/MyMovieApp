package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.NaverArticleResponse
import com.navermovie.data.remote.response.PosterResult

interface NaverSearchDataSource {

    suspend fun getMoviePoster(query: String): PosterResult

    suspend fun getMovieArticle(query: String): NaverArticleResponse
}