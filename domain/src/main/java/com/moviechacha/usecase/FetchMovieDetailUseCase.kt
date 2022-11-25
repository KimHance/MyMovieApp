package com.moviechacha.usecase

import com.moviechacha.entity.Movie
import com.moviechacha.repository.RemoteMovieRepository
import javax.inject.Inject

class FetchMovieDetailUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke(movie: Movie) = repository.fetchMovieDetail(movie)
}