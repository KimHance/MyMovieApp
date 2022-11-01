package com.navermovie.repository

import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.entity.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteMovieRepository {
    fun getMovieList(): Flow<List<Movie>?>

    fun fetchMovieDetail(movie: Movie): Flow<Movie?>

    fun fetchMoviePoster(movie: Movie): Flow<Movie?>

    fun getMovieTeaser(query: String): Flow<String?>

    fun getImageUrl(movie: Movie): Flow<Actor>

    fun getMovieArticle(movie: Movie): Flow<List<Article>?>

    fun getMoviePlot(movie: Movie): Flow<String>
}