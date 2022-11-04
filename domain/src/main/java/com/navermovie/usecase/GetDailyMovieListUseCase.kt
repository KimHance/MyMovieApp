package com.navermovie.usecase

import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetDailyMovieListUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke() = repository.getDailyMovieList()
}