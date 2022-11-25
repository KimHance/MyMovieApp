package com.moviechacha.data.local.datasource.impl

import com.moviechacha.data.local.dao.CachedActorDao
import com.moviechacha.data.local.dao.CachedArticleDao
import com.moviechacha.data.local.dao.CachedStoryDao
import com.moviechacha.data.local.datasource.CachedDataSource
import com.moviechacha.data.local.dto.CachedActorImageEntity
import com.moviechacha.data.local.dto.CachedArticleEntity
import com.moviechacha.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CachedDataSourceImpl @Inject constructor(
    private val cachedActorDao: CachedActorDao,
    private val cachedArticleDao: CachedArticleDao,
    private val cachedStoryDao: CachedStoryDao
) : CachedDataSource {
    override fun getActorList(code: String): Flow<CachedActorImageEntity> =
        cachedActorDao.getActorList(code)

    override fun getArticleList(code: String): Flow<CachedArticleEntity> =
        cachedArticleDao.getArticleList(code)

    override fun getMovieStory(code: String): Flow<CachedStoryEntity> =
        cachedStoryDao.getMovieStory(code)
}