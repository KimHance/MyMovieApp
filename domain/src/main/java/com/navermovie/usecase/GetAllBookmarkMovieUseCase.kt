package com.navermovie.usecase

import com.navermovie.repository.MovieRepository
import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetAllBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke() = movieRepository.getAllBookmarkedMovieList()
}