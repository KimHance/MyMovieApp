package com.navermovie.data.remote.datasource

import com.navermovie.data.remote.response.KakaoImageResponse
import kotlinx.coroutines.flow.Flow

interface KakaoSearchDataSource {
    suspend fun getImage(query: String): Flow<KakaoImageResponse?>
}