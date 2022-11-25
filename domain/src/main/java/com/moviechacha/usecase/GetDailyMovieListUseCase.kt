package com.moviechacha.usecase

import com.moviechacha.repository.RemoteMovieRepository
import javax.inject.Inject

class GetDailyMovieListUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke() = repository.getDailyMovieList()
}