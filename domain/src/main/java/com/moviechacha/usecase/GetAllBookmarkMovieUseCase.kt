package com.moviechacha.usecase

import com.moviechacha.repository.MovieRepository
import com.moviechacha.repository.RemoteMovieRepository
import javax.inject.Inject

class GetAllBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke() = movieRepository.getAllBookmarkedMovieList()
}