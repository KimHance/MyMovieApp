package com.navermovie.repository

import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import kotlinx.coroutines.flow.Flow

interface LocalMovieRepository {
    fun getActorList(code: String): Flow<List<Actor>?>
    fun getArticleList(code: String): Flow<List<Article>>?
    fun getMovieStory(code: String): Flow<String>?
    suspend fun deleteCachedActor(date: Long)
    suspend fun deleteCachedArticle(date: Long)
    suspend fun saveActorList(code: String, list: List<Actor>, date: Long)
    suspend fun saveArticle(code: String, list: List<Article>, date: Long)
    suspend fun saveMovieStory(code: String, story: String, date: Long)
}