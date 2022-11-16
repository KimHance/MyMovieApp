package com.navermovie.data.repositoryimpl

import com.navermovie.PLOT_ERROR
import com.navermovie.data.local.dao.BookmarkDao
import com.navermovie.data.local.dao.CachedActorDao
import com.navermovie.data.local.dao.CachedArticleDao
import com.navermovie.data.local.dao.CachedStoryDao
import com.navermovie.data.local.datasource.BookmarkDataSource
import com.navermovie.data.local.datasource.CachedDataSource
import com.navermovie.data.local.dto.CachedActorImageEntity
import com.navermovie.data.local.dto.CachedArticleEntity
import com.navermovie.data.local.dto.CachedStoryEntity
import com.navermovie.data.remote.datasource.KakaoSearchDataSource
import com.navermovie.data.remote.datasource.KmdbSearchDataSource
import com.navermovie.data.remote.datasource.NaverSearchDataSource
import com.navermovie.di.DispatcherModule
import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkDataSource,
    private val cachedDataSource: CachedDataSource,
    private val naverSearchDataSource: NaverSearchDataSource,
    private val kakaoSearchDataSource: KakaoSearchDataSource,
    private val kmdbSearchDataSource: KmdbSearchDataSource,
    private val cachedActorDao: CachedActorDao,
    private val cachedArticleDao: CachedArticleDao,
    private val cachedStoryDao: CachedStoryDao,
    private val bookmarkDao: BookmarkDao,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : MovieRepository {
    override fun getActorImageList(code: String): Flow<Pair<List<Actor>?, Boolean>> = flow {
        if (cachedActorDao.isActorExists(code)) {
            cachedDataSource.getActorList(code).collect { cachedData ->
                emit(Pair(cachedData.actorList, true))
            }
        } else {
            emit(Pair(null, false))
        }
    }.flowOn(dispatcherIO)

    override fun getArticleList(code: String): Flow<Pair<List<Article>?, Boolean>> = flow {
        if (cachedArticleDao.isArticleExists(code)) {
            cachedDataSource.getArticleList(code).collect { cachedData ->
                emit(Pair(cachedData.articleList, true))
            }
        } else {
            emit(Pair(null, false))
        }
    }.flowOn(dispatcherIO)

    override fun getMovieStory(code: String): Flow<Pair<String, Boolean>> = flow {
        if (cachedStoryDao.isPlotExists(code)) {
            cachedDataSource.getMovieStory(code).collect { cachedData ->
                emit(Pair(cachedData.movieStory, true))
            }
        } else {
            emit(Pair(PLOT_ERROR, false))
        }
    }.flowOn(dispatcherIO)

    override suspend fun deleteCachedData(date: Long) {
        cachedActorDao.deleteCachedActor(date)
        cachedArticleDao.deleteCachedArticle(date)
    }

    override suspend fun saveActorImageList(code: String, list: List<Actor>, date: Long) {
        val entity = CachedActorImageEntity(code, list, date)
        cachedActorDao.saveActorList(entity)
    }

    override suspend fun saveArticleList(code: String, list: List<Article>, date: Long) {
        val entity = CachedArticleEntity(code, list, date)
        cachedArticleDao.saveArticleList(entity)
    }

    override suspend fun saveMovieStory(code: String, story: String, date: Long) {
        val entity = CachedStoryEntity(code, story, date)
        cachedStoryDao.saveMovieStory(entity)
    }

}