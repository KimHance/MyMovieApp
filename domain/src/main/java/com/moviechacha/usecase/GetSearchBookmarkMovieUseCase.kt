package com.moviechacha.usecase

import com.moviechacha.repository.MovieRepository
import javax.inject.Inject

class GetSearchBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(query: String) = movieRepository.getSearchBookmarkedMovieList(query)
}