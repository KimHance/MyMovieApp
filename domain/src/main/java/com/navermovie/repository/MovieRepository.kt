package com.navermovie.repository

import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getActorImageList(code: String): Flow<Pair<List<Actor>?, Boolean>>
    suspend fun getArticleList(code: String): Flow<Pair<List<Article>?, Boolean>>
    suspend fun getMovieStory(code: String): Flow<Pair<String, Boolean>>
    suspend fun deleteCachedData(date: Long)
    suspend fun saveActorImageList(code: String, list: List<Actor>, date: Long)
    suspend fun saveArticleList(code: String, list: List<Article>, date: Long)
    suspend fun saveMovieStory(code: String, story: String, date: Long)
}