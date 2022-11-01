package com.navermovie.presentation.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Movie
import com.navermovie.presentation.view.boxoffice.BoxOfficeUiState
import com.navermovie.usecase.DeleteCachedDataUseCase
import com.navermovie.usecase.FetchMovieDetailUseCase
import com.navermovie.usecase.FetchMoviePosterUseCase
import com.navermovie.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchMoviePosterUseCase: FetchMoviePosterUseCase,
    private val deleteCachedDataUseCase: DeleteCachedDataUseCase
) : ViewModel() {

    private val emptyMovieList = mutableListOf<Movie>().apply {
        repeat(10) { add(Movie()) }
    }.toList()

    private val _boxOfficeUiState =
        MutableStateFlow<BoxOfficeUiState>(BoxOfficeUiState.Loading(emptyMovieList))
    val boxOfficeUiState = _boxOfficeUiState.asStateFlow()

    fun deleteCachedData(date: Long) {
        viewModelScope.launch {
            deleteCachedDataUseCase(date)
        }
    }

    fun getMovieList() {
        viewModelScope.launch {
            getMovieListUseCase().collect { list ->
                val start = System.currentTimeMillis()
                flow {
                    emit(
                        list?.map { unFetchedMovie ->
                            fetchMovieDetailUseCase(unFetchedMovie)
                                .zip(fetchMoviePosterUseCase(unFetchedMovie)) { detailMovie, posterMovie ->
                                    unFetchedMovie.copy(
                                        openDate = detailMovie?.openDate,
                                        actors = detailMovie?.actors,
                                        genres = detailMovie?.genres,
                                        audits = detailMovie?.audits,
                                        directors = detailMovie?.directors,
                                        showTime = detailMovie?.showTime,
                                        poster = posterMovie?.poster,
                                        rating = posterMovie?.rating,
                                        isFetched = true
                                    )
                                }.catch {
                                    _boxOfficeUiState.value = BoxOfficeUiState.Error
                                }.first()
                        }
                    )
                }.catch {
                    _boxOfficeUiState.value = BoxOfficeUiState.Error
                }.collect { fetchedList ->
                    _boxOfficeUiState.value = BoxOfficeUiState.Success(fetchedList)
                    val end = System.currentTimeMillis()
                    Log.d("시간차이", "${end - start}")
                }
            }
        }
    }
}