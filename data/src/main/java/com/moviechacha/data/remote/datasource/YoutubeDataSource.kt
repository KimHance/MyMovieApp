package com.moviechacha.data.remote.datasource

import com.moviechacha.data.remote.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow

interface YoutubeDataSource {
    suspend fun getMovieTeaserLink(title: String): Flow<YoutubeResponse?>
}