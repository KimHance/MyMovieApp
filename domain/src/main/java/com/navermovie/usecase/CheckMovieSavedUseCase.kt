package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.MovieRepository
import javax.inject.Inject

class CheckMovieSavedUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movie: Movie) = movieRepository.checkBookmarkMovie(movie)
}