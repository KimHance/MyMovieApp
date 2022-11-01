package com.navermovie.usecase

import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetYoutubeLinkIdUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke(query: String) = repository.getMovieTeaser(query)
}