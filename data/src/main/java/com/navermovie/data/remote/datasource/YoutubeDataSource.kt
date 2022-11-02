package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow

interface YoutubeDataSource {
    suspend fun getMovieTeaserLink(title: String): Flow<YoutubeResponse?>
}