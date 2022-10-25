package com.navermovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.navermovie.data.local.dto.CachedActorImageEntity
import com.navermovie.data.local.dto.CachedArticleEntity
import com.navermovie.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedActorDao {
    @Query("SELECT * FROM CACHED_ACTOR_IMAGE_TABLE WHERE movieCode =:code")
    fun getActorList(code: String): Flow<CachedActorImageEntity>

    @Query("DELETE FROM CACHED_ACTOR_IMAGE_TABLE WHERE((:date - date)/ 1000/ (24*60*60)) >= 7")
    suspend fun deleteCachedActor(date: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActorList(cachedActor: CachedActorImageEntity)
}

@Dao
interface CachedArticleDao {
    @Query("SELECT * FROM CACHED_ARTICLE_TABLE WHERE movieCode =:code")
    fun getArticleList(code: String): Flow<CachedArticleEntity>

    @Query("DELETE FROM CACHED_ARTICLE_TABLE WHERE((:date - date)/ 1000/ (24*60*60)) >= 3")
    suspend fun deleteCachedArticle(date: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticleList(cachedArticle: CachedArticleEntity)
}

@Dao
interface CachedStoryDao {
    @Query("SELECT * FROM CACHED_MOVIE_STORY WHERE movieCode =:code")
    fun getMovieStory(code: String): Flow<CachedStoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieStory(cachedStory: CachedStoryEntity)
}