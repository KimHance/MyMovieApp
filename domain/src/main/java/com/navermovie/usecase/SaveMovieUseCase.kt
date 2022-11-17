package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.MovieRepository
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) = movieRepository.saveBookmarkMovie(movie)
}