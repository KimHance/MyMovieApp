package com.navermovie.usecase

import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class GetActorImageUseCase @Inject constructor(
    private val repository: RemoteMovieRepository
) {
    suspend operator fun invoke(title: String, actor: String) = repository.getImageUrl(title, actor)
}