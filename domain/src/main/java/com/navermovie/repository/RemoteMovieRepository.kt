package com.navermovie.repository

import com.navermovie.entity.Actor
import com.navermovie.entity.Movie

interface RemoteMovieRepository {
    suspend fun getMovieList(): List<Movie>?

    suspend fun fetchMovieDetail(movie: Movie): Movie

    suspend fun fetchMoviePoster(movie: Movie): Movie

    suspend fun getMovieTeaser(query: String): String?

    suspend fun getImageUrl(movie: Movie): List<Actor>?
}