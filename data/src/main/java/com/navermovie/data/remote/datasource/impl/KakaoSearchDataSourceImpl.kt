package com.navermovie.data.remote.datasource.impl

import com.navermovie.data.remote.datasource.KakaoSearchDataSource
import com.navermovie.data.remote.response.KakaoImageResponse
import com.navermovie.data.remote.service.KakaoSearchService
import javax.inject.Inject

class KakaoSearchDataSourceImpl @Inject constructor(
    private val kakaoSearchService: KakaoSearchService
) : KakaoSearchDataSource {

    override suspend fun getImage(query: String): KakaoImageResponse =
        kakaoSearchService.getActorImage(query)
}