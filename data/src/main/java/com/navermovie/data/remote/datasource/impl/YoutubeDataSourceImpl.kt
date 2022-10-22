package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.YoutubeDataSource
import com.navermovie.data.remote.response.YoutubeResponse
import com.navermovie.data.remote.service.YoutubeService
import javax.inject.Inject

class YoutubeDataSourceImpl @Inject constructor(
    private val youtubeService: YoutubeService
) : YoutubeDataSource {
    override suspend fun getMovieTeaserLink(title: String): YoutubeResponse =
        run { youtubeService.getYoutubeLink(title) }
}