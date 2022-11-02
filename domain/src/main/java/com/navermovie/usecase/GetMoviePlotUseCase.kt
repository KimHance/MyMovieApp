package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.MovieRepository
import com.navermovie.repository.RemoteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviePlotUseCase @Inject constructor(
    private val remoteRepository: RemoteMovieRepository,
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie): Flow<Pair<String, Boolean>> =
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
        }.flowOn(Dispatchers.Default)
}