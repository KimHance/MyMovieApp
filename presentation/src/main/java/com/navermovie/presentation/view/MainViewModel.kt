package com.navermovie.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Movie
import com.navermovie.usecase.FetchMovieDetailUseCase
import com.navermovie.usecase.FetchMoviePosterUseCase
import com.navermovie.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchMoviePosterUseCase: FetchMoviePosterUseCase,
) : ViewModel() {

    private val emptyMovieList = mutableListOf<Movie>().apply {
        repeat(10) { add(Movie()) }
    }.toList()

    private val _movieList = MutableStateFlow(emptyMovieList)
    val movieList = _movieList.asStateFlow()


    // flatMap
    fun fetchMovieList() {
        viewModelScope.launch {
            val fetchMovieDetailJob = async {
                getMovieListUseCase()?.map {
                    fetchMovieDetailUseCase(it)
                }
            }
            val fetchMoviePosterJob = async {
                fetchMovieDetailJob.await()?.map {
                    fetchMoviePosterUseCase(it)
                }
            }
            fetchMoviePosterJob.await()?.let { fetchedMovieList ->
                _movieList.value = fetchedMovieList
            }
        }
    }
}