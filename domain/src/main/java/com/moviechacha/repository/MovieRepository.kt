package com.moviechacha.repository

import com.moviechacha.entity.Actor
import com.moviechacha.entity.Article
import com.moviechacha.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getActorImageList(code: String): Flow<Pair<List<Actor>?, Boolean>>
    fun getArticleList(code: String): Flow<Pair<List<Article>?, Boolean>>
    fun getMovieStory(code: String): Flow<Pair<String, Boolean>>
    fun getAllBookmarkedMovieList(): Flow<List<Movie>>
    fun getSearchBookmarkedMovieList(query: String): Flow<List<Movie>>
    fun checkBookmarkMovie(movie: Movie): Flow<Boolean>
    suspend fun deleteCachedData(date: Long)
    suspend fun saveActorImageList(code: String, list: List<Actor>, date: Long)
    suspend fun saveArticleList(code: String, list: List<Article>, date: Long)
    suspend fun saveMovieStory(code: String, story: String, date: Long)
    suspend fun saveBookmarkMovie(movie: Movie)
    suspend fun deleteBookmark(movie: Movie)
}