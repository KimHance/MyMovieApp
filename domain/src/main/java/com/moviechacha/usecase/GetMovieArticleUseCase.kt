package com.moviechacha.usecase

import com.moviechacha.entity.Article
import com.moviechacha.entity.Movie
import com.moviechacha.repository.MovieRepository
import com.moviechacha.repository.RemoteMovieRepository
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