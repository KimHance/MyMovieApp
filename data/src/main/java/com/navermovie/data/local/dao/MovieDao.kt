package com.navermovie.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.navermovie.data.local.dto.CachedActorImageEntity
import com.navermovie.data.local.dto.CachedArticleEntity
import com.navermovie.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedActorDao {
    @Query("SELECT * FROM CACHED_ACTOR_IMAGE_TABLE WHERE movieCode =:code")
    fun getActorList(code: String): Flow<CachedActorImageEntity>?

    @Query("DELETE FROM CACHED_ACTOR_IMAGE_TABLE")
    suspend fun deleteAllCachedActor()
}

@Dao
interface CachedArticleDao {
    @Query("SELECT * FROM CACHED_ARTICLE_TABLE WHERE movieCode =:code")
    fun getArticleList(code: String): Flow<CachedArticleEntity>?

    @Query("DELETE FROM CACHED_ARTICLE_TABLE")
    suspend fun deleteAllCachedArticle()
}

@Dao
interface CachedStoryDao {
    @Query("SELECT * FROM CACHED_MOVIE_STORY WHERE movieCode =:code")
    fun getMovieStory(code: String): Flow<CachedStoryEntity>?
}