package com.navermovie.usecase

import com.navermovie.entity.Movie
import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetMoviePlotUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke(movie: Movie) = repository.getMoviePlot(movie)
}