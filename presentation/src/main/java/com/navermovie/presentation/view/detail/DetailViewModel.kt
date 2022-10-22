package com.navermovie.presentation.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.usecase.GetYoutubeLinkIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getYoutubeLinkIdUseCase: GetYoutubeLinkIdUseCase
) : ViewModel() {

    private val _selectedMovieLinkId = MutableSharedFlow<String>()
    val selectedMovieLinkId = _selectedMovieLinkId.asSharedFlow()

    fun setYoutubeVideoId(title: String) {
        viewModelScope.launch {
            runCatching {
                getYoutubeLinkIdUseCase(title)?.let {
                    _selectedMovieLinkId.emit(it)
                }
            }
        }
    }
}