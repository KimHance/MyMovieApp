package com.moviechacha.usecase

import com.moviechacha.entity.Actor
import com.moviechacha.repository.MovieRepository
import javax.inject.Inject

class SaveActorListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(code: String, list: List<Actor>, date: Long) =
        repository.saveActorImageList(code, list, date)
}