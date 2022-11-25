package com.moviechacha.presentation.view.boxoffice

import com.moviechacha.entity.Movie

sealed class BoxOfficeUiState {
    data class Success(val data: List<Movie>?) : BoxOfficeUiState()
    data class Loading(val data: List<Movie>) : BoxOfficeUiState()
    data class Empty(val query: String) : BoxOfficeUiState()

    object Error : BoxOfficeUiState()
}
