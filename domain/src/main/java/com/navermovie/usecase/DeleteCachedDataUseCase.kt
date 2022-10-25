package com.navermovie.usecase

import com.navermovie.repository.LocalMovieRepository
import javax.inject.Inject

class DeleteCachedDataUseCase @Inject constructor(
    private val repository: LocalMovieRepository
) {
    suspend operator fun invoke(date: Long) {
        repository.apply {
            deleteCachedActor(date)
            deleteCachedArticle(date)
        }
    }
}