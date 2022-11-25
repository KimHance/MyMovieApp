package com.moviechacha.usecase

import com.moviechacha.repository.RemoteMovieRepository
import javax.inject.Inject

class GetYoutubeLinkIdUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    operator fun invoke(query: String) = repository.getMovieTeaser(query)
}