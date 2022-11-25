package com.moviechacha.usecase

import com.moviechacha.entity.Movie
import com.moviechacha.repository.MovieRepository
import com.moviechacha.repository.RemoteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviePlotUseCase @Inject constructor(
    private val remoteRepository: RemoteMovieRepository,
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movie: Movie): Flow<Pair<String, Boolean>> =
        flow {
            movieRepository.getMovieStory(movie.movieCd).collect { data ->
                if (data.second) {
                    emit(data)
                } else {
                    remoteRepository.getMoviePlot(movie).collect { plot ->
                        emit(Pair(plot, false))
                    }
                }
            }
        }
}