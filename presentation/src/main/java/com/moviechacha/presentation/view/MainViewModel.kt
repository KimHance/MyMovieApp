package com.moviechacha.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviechacha.entity.Movie
import com.moviechacha.presentation.view.boxoffice.BoxOfficeUiState
import com.moviechacha.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDailyMovieListUseCase: GetDailyMovieListUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchMoviePosterUseCase: FetchMoviePosterUseCase,
    private val deleteCachedDataUseCase: DeleteCachedDataUseCase,
    private val getWeeklyMovieListUseCase: GetWeeklyMovieListUseCase,
) : ViewModel() {

    private val emptyMovieList = mutableListOf<Movie>().apply {
        repeat(10) { add(Movie()) }
    }.toList()

    private val _dailyBoxOfficeUiState =
        MutableStateFlow<BoxOfficeUiState>(BoxOfficeUiState.Loading(emptyList()))
    val dailyBoxOfficeUiState = _dailyBoxOfficeUiState.asStateFlow()

    private val _weeklyBoxOfficeUiState =
        MutableStateFlow<BoxOfficeUiState>(BoxOfficeUiState.Loading(emptyList()))
    val weeklyBoxOfficeUiState = _weeklyBoxOfficeUiState.asStateFlow()

    fun deleteCachedData(date: Long) {
        viewModelScope.launch {
            deleteCachedDataUseCase(date)
        }
    }

    @OptIn(FlowPreview::class)
    fun getWeeklyBoxOfficeList() {
        _weeklyBoxOfficeUiState.value = BoxOfficeUiState.Loading(emptyMovieList)
        val movieList = mutableListOf<Movie>()
        viewModelScope.launch {
            getWeeklyMovieListUseCase().collect { unFetchedMovie ->
                fetchMovieDetailUseCase(unFetchedMovie).flatMapMerge { detailFetchedMovie ->
                    fetchMoviePosterUseCase(detailFetchedMovie)
                }.catch {
                    _weeklyBoxOfficeUiState.value = BoxOfficeUiState.Error
                }.collect { movie ->
                    movieList.add(movie)
                    if (movieList.size == 10) {
                        _weeklyBoxOfficeUiState.update { BoxOfficeUiState.Success(movieList) }
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun getDailyBoxOfficeList() {
        _dailyBoxOfficeUiState.value = BoxOfficeUiState.Loading(emptyMovieList)
        val movieList = mutableListOf<Movie>()
        viewModelScope.launch {
            getDailyMovieListUseCase().collect { unFetchedMovie ->
                fetchMovieDetailUseCase(unFetchedMovie).flatMapMerge { detailFetchedMovie ->
                    fetchMoviePosterUseCase(detailFetchedMovie)
                }.catch {
                    _dailyBoxOfficeUiState.value = BoxOfficeUiState.Error
                }.collect { movie ->
                    movieList.add(movie)
                    if (movieList.size == 10) {
                        _dailyBoxOfficeUiState.update { BoxOfficeUiState.Success(movieList) }
                    }
                }
            }
        }
    }

    fun setNetworkError() {
        _dailyBoxOfficeUiState.value = BoxOfficeUiState.Error
        _weeklyBoxOfficeUiState.value = BoxOfficeUiState.Error
    }
}