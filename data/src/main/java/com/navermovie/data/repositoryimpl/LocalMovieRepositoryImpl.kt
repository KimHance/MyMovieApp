package com.navermovie.data.repositoryimpl

import com.navermovie.data.local.dao.CachedActorDao
import com.navermovie.data.local.dao.CachedArticleDao
import com.navermovie.data.local.dao.CachedStoryDao
import com.navermovie.data.local.datasource.CachedDataSource
import com.navermovie.data.local.dto.CachedActorImageEntity
import com.navermovie.data.local.dto.CachedArticleEntity
import com.navermovie.di.DispatcherModule
import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.repository.LocalMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalMovieRepositoryImpl @Inject constructor(
    private val dataSource: CachedDataSource,
    private val cachedActorDao: CachedActorDao,
    private val cachedArticleDao: CachedArticleDao,
    private val cachedStoryDao: CachedStoryDao,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : LocalMovieRepository {
    override fun getActorList(code: String) = flow {
        with(dataSource.getActorList(code)) {
            this.collect { cachedEntity ->
                if (cachedEntity == null) {
                    emit(null)
                } else {
                    emit(cachedEntity.actorList)
                }
            }
        }
    }.flowOn(dispatcherIO)

    override fun getArticleList(code: String) = flow {
        with(dataSource.getArticleList(code)) {
            this.collect { cachedEntity ->
                if (cachedEntity == null) {
                    emit(null)
                } else {
                    emit(cachedEntity.articleList)
                }
            }
        }
    }.flowOn(dispatcherIO)

    override fun getMovieStory(code: String) =
        dataSource.getMovieStory(code).filterIsInstance<String>()

    override suspend fun deleteCachedActor(date: Long) =
        cachedActorDao.deleteCachedActor(date)

    override suspend fun deleteCachedArticle(date: Long) =
        cachedArticleDao.deleteCachedArticle(date)

    override suspend fun saveActorList(code: String, list: List<Actor>, date: Long) {
        val entity = CachedActorImageEntity(code, list, date)
        cachedActorDao.saveActorList(entity)
    }

    override suspend fun saveArticle(code: String, list: List<Article>, date: Long) {
        val entity = CachedArticleEntity(code, list, date)
        cachedArticleDao.saveArticleList(entity)
    }

    override suspend fun saveMovieStory(code: String, story: String, date: Long) {}
}