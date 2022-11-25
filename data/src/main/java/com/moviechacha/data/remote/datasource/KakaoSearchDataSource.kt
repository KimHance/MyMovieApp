package com.moviechacha.data.remote.datasource

import com.moviechacha.data.remote.response.KakaoImageResponse

interface KakaoSearchDataSource {
    suspend fun getImage(query: String): KakaoImageResponse
}