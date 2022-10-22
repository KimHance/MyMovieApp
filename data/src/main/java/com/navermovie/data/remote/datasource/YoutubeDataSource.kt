package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.YoutubeResponse

interface YoutubeDataSource {
    suspend fun getMovieTeaserLink(title: String): YoutubeResponse
}