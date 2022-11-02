package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.NaverArticleResponse
import com.navermovie.data.remote.response.PosterResult
import kotlinx.coroutines.flow.Flow

interface NaverSearchDataSource {

    suspend fun getMoviePoster(query: String): Flow<PosterResult?>

    suspend fun getMovieArticle(query: String): Flow<NaverArticleResponse?>
}