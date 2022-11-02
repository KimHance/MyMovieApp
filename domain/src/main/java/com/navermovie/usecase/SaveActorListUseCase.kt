package com.navermovie.usecase

import com.navermovie.entity.Actor
import com.navermovie.repository.MovieRepository
import javax.inject.Inject

class SaveActorListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(code: String, list: List<Actor>, date: Long) =
        repository.saveActorImageList(code, list, date)
}