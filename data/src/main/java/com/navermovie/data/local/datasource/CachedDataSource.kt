package com.navermovie.data.local.datasource

import com.navermovie.data.local.dto.CachedActorImageEntity
import com.navermovie.data.local.dto.CachedArticleEntity
import com.navermovie.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow

interface CachedDataSource {
    fun getActorList(code: String): Flow<CachedActorImageEntity>
    fun getArticleList(code: String): Flow<CachedArticleEntity>
    fun getMovieStory(code: String): Flow<CachedStoryEntity>
}