package com.moviechacha.data.remote.datasource.impl

import com.moviechacha.data.remote.datasource.YoutubeDataSource
import com.moviechacha.data.remote.service.YoutubeService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class YoutubeDataSourceImpl @Inject constructor(
    private val youtubeService: YoutubeService
) : YoutubeDataSource {
    override suspend fun getMovieTeaserLink(title: String) = flow {
        emit(
            runCatching {
                youtubeService.getYoutubeLink(title)
            }.getOrNull()
        )
    }
}