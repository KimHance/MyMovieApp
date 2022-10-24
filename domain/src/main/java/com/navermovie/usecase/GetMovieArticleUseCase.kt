package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetMovieArticleUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    suspend operator fun invoke(movie: Movie) = repository.getMovieArticle(movie)
}