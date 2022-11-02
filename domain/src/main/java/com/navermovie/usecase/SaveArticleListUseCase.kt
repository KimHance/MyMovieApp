package com.navermovie.usecase

import com.navermovie.entity.Article
import com.navermovie.repository.MovieRepository
import javax.inject.Inject

class SaveArticleListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(code: String, list: List<Article>, date: Long) =
        repository.saveArticleList(code, list, date)
}