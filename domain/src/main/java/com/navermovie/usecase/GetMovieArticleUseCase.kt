package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.LocalMovieRepository
import com.navermovie.repository.RemoteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieArticleUseCase @Inject constructor(
    private val remoteRepository: RemoteMovieRepository,
    private val localRepository: LocalMovieRepository,
) {
    suspend operator fun invoke(movie: Movie, date: Long) = flow {
        localRepository.getArticleList(movie.movieCd).collect {
            if (it != null) {
                emit(it)
            } else {
                remoteRepository.getMovieArticle(movie).also { list ->
                    list?.let {
                        localRepository.saveArticle(movie.movieCd, list, date)
                    }
                }
            }
        }
    }.flowOn(Dispatchers.Default)
}