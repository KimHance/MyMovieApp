package com.navermovie.presentation.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Movie
import com.navermovie.presentation.view.boxoffice.BoxOfficeUiState
import com.navermovie.usecase.FetchMovieDetailUseCase
import com.navermovie.usecase.FetchMoviePosterUseCase
import com.navermovie.usecase.GetSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchListUseCase: GetSearchListUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchMoviePosterUseCase: FetchMoviePosterUseCase
) : ViewModel() {

    private val _searchList =
        MutableStateFlow<BoxOfficeUiState>(BoxOfficeUiState.Loading(emptyList()))
    val searchList = _searchList.asStateFlow()

    var isSearchLoading = MutableStateFlow(false)

    fun getSearchList(query: String) {
        isSearchLoading.value = true
        viewModelScope.launch {
            getSearchListUseCase(query).catch {
                _searchList.value = BoxOfficeUiState.Error
                isSearchLoading.value = false
            }.collect { unfetchedList ->
                isSearchLoading.value = false
                _searchList.update {
                    if (unfetchedList.isEmpty()) {
                        BoxOfficeUiState.Empty(query)
                    } else {
                        BoxOfficeUiState.Loading(unfetchedList)
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun fetchList() {
        viewModelScope.launch {
            val unfetchedList = _searchList.value as BoxOfficeUiState.Loading
            val fetchedMovieList = mutableListOf<Movie>()
            unfetchedList.data.onEach { unfetchedMovie ->
                fetchMovieDetailUseCase(unfetchedMovie).catch {
                    _searchList.value = BoxOfficeUiState.Error
                }.flatMapMerge { detailFetchedMovie ->
                    fetchMoviePosterUseCase(detailFetchedMovie)
                }.catch {
                    _searchList.value = BoxOfficeUiState.Error
                }.collect { movie ->
                    fetchedMovieList.add(movie)
                    if (fetchedMovieList.size == unfetchedList.data.size) {
                        _searchList.update {
                            BoxOfficeUiState.Success(fetchedMovieList)
                        }
                    }
                }
            }
        }
    }
}