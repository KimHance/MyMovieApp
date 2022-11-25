package com.moviechacha.data.local.datasource

import com.moviechacha.data.local.dto.CachedActorImageEntity
import com.moviechacha.data.local.dto.CachedArticleEntity
import com.moviechacha.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow

interface CachedDataSource {
    fun getActorList(code: String): Flow<CachedActorImageEntity>
    fun getArticleList(code: String): Flow<CachedArticleEntity>
    fun getMovieStory(code: String): Flow<CachedStoryEntity>
}