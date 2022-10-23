package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KakaoImageResponse

interface KakaoSearchDataSource {
    suspend fun getImage(query: String): KakaoImageResponse
}