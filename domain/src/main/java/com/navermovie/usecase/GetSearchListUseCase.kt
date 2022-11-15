package com.navermovie.usecase

import com.navermovie.repository.RemoteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSearchListUseCase @Inject constructor(
    private val remoteMovieRepository: RemoteMovieRepository
) {
    operator fun invoke(query: String) =
        remoteMovieRepository.getSearchList(query)
}