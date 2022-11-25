package com.moviechacha.data.remote.datasource.impl

import com.moviechacha.data.remote.datasource.KakaoSearchDataSource
import com.moviechacha.data.remote.response.KakaoImageResponse
import com.moviechacha.data.remote.service.KakaoSearchService
import javax.inject.Inject

class KakaoSearchDataSourceImpl @Inject constructor(
    private val kakaoSearchService: KakaoSearchService
) : KakaoSearchDataSource {

    override suspend fun getImage(query: String): KakaoImageResponse =
        kakaoSearchService.getActorImage(query)
}