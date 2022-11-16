package com.navermovie.usecase

import com.navermovie.entity.Article
import com.navermovie.entity.Movie
import com.navermovie.repository.MovieRepository
import com.navermovie.repository.RemoteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieArticleUseCase @Inject constructor(
    private val remoteRepository: RemoteMovieRepository,
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(movie: Movie): Flow<Pair<List<Article>?, Boolean>> =
        flow {
            movieRepository.getArticleList(movie.movieCd).collect { data ->
                if (data.second) {
                    emit(data)
                } else {
                    remoteRepository.getMovieArticle(movie).collect { list ->
                        emit(Pair(list, false))
                    }
                }
            }
        }
}