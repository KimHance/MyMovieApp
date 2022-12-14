package com.moviechacha.repository

import com.moviechacha.entity.Actor
import com.moviechacha.entity.Article
import com.moviechacha.entity.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteMovieRepository {
    fun getDailyMovieList(): Flow<Movie>

    fun getWeeklyMovieList(): Flow<Movie>

    fun fetchMovieDetail(movie: Movie): Flow<Movie>

    fun fetchMoviePoster(movie: Movie): Flow<Movie>

    fun getMovieTeaser(query: String): Flow<String?>

    suspend fun getImageUrl(movie: Movie): Flow<List<Actor>>

    fun getMovieArticle(movie: Movie): Flow<List<Article>?>

    fun getMoviePlot(movie: Movie): Flow<String>

    fun getSearchList(query: String): Flow<List<Movie>>
}