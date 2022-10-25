package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.LocalMovieRepository
import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetMovieArticleUseCase @Inject constructor(
    private val remoteRepository: RemoteMovieRepository,
    private val localRepository: LocalMovieRepository
) {
    suspend operator fun invoke(movie: Movie) = remoteRepository.getMovieArticle(movie)
}