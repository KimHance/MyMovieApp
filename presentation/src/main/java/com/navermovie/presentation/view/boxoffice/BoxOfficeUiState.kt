package com.navermovie.presentation.view.boxoffice

import com.navermovie.entity.Movie

sealed class BoxOfficeUiState {
    data class Success(val data: List<Movie>) : BoxOfficeUiState()
    data class Error(val error: Throwable) : BoxOfficeUiState()
    object Loading : BoxOfficeUiState()
}
