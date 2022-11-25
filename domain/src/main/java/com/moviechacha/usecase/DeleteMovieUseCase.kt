package com.moviechacha.usecase

import com.moviechacha.entity.Movie
import com.moviechacha.repository.MovieRepository
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) = movieRepository.deleteBookmark(movie)
}