package com.navermovie.usecase

import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke() = repository.getMovieList()
}