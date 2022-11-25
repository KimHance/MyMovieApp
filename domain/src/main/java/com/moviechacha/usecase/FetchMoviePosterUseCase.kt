package com.moviechacha.usecase

import com.moviechacha.entity.Movie
import com.moviechacha.repository.RemoteMovieRepository
import javax.inject.Inject

class FetchMoviePosterUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke(movie: Movie) = repository.fetchMoviePoster(movie)
}