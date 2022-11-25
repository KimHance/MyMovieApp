package com.moviechacha.data.remote.datasource

import com.moviechacha.data.remote.response.NaverArticleResponse
import com.moviechacha.data.remote.response.PosterResult
import kotlinx.coroutines.flow.Flow

interface NaverSearchDataSource {

    suspend fun getMoviePoster(query: String): Flow<PosterResult?>

    suspend fun getMovieArticle(query: String): Flow<NaverArticleResponse?>
}