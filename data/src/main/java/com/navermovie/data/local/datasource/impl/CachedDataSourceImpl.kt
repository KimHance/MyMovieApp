package com.navermovie.data.local.datasource.impl

import com.navermovie.data.local.dao.CachedActorDao
import com.navermovie.data.local.dao.CachedArticleDao
import com.navermovie.data.local.dao.CachedStoryDao
import com.navermovie.data.local.datasource.CachedDataSource
import com.navermovie.data.local.dto.CachedActorImageEntity
import com.navermovie.data.local.dto.CachedArticleEntity
import com.navermovie.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CachedDataSourceImpl @Inject constructor(
    private val cachedActorDao: CachedActorDao,
    private val cachedArticleDao: CachedArticleDao,
    private val cachedStoryDao: CachedStoryDao
) : CachedDataSource {
    override fun getActorList(code: String): Flow<CachedActorImageEntity>? =
        cachedActorDao.getActorList(code)

    override fun getArticleList(code: String): Flow<CachedArticleEntity>? =
        cachedArticleDao.getArticleList(code)

    override fun getMovieStory(code: String): Flow<CachedStoryEntity>? =
        cachedStoryDao.getMovieStory(code)

    override suspend fun deleteAllCachedActor() =
        cachedActorDao.deleteAllCachedActor()

    override suspend fun deleteAllCachedArticle() =
        cachedArticleDao.deleteAllCachedArticle()
}