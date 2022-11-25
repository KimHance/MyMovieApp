package com.moviechacha.usecase

import com.moviechacha.repository.MovieRepository
import javax.inject.Inject

class DeleteCachedDataUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(date: Long) = repository.deleteCachedData(date)
}