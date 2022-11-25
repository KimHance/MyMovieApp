package com.moviechacha.usecase

import com.moviechacha.entity.Movie
import com.moviechacha.repository.MovieRepository
import javax.inject.Inject

class CheckMovieSavedUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movie: Movie) = movieRepository.checkBookmarkMovie(movie)
}