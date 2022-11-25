package com.moviechacha.data.local.dao

import androidx.room.*
import com.moviechacha.data.local.dto.BookmarkedMovie
import com.moviechacha.data.local.dto.CachedActorImageEntity
import com.moviechacha.data.local.dto.CachedArticleEntity
import com.moviechacha.data.local.dto.CachedStoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedActorDao {
    @Query("SELECT * FROM CACHED_ACTOR_IMAGE_TABLE WHERE movieCode =:code")
    fun getActorList(code: String): Flow<CachedActorImageEntity>

    @Query("DELETE FROM CACHED_ACTOR_IMAGE_TABLE WHERE((:date - date)/ 1000/ (24*60*60)) >= 7")
    suspend fun deleteCachedActor(date: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveActorList(cachedActor: CachedActorImageEntity)

    @Query("SELECT EXISTS(SELECT * FROM CACHED_ACTOR_IMAGE_TABLE WHERE movieCode =:code)")
    fun isActorExists(code: String): Boolean
}

@Dao
interface CachedArticleDao {
    @Query("SELECT * FROM CACHED_ARTICLE_TABLE WHERE movieCode =:code")
    fun getArticleList(code: String): Flow<CachedArticleEntity>

    @Query("DELETE FROM CACHED_ARTICLE_TABLE WHERE((:date - date)/ 1000/ (24*60*60)) >= 3")
    suspend fun deleteCachedArticle(date: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveArticleList(cachedArticle: CachedArticleEntity)

    @Query("SELECT EXISTS(SELECT * FROM CACHED_ARTICLE_TABLE WHERE movieCode =:code)")
    fun isArticleExists(code: String): Boolean
}

@Dao
interface CachedStoryDao {
    @Query("SELECT * FROM CACHED_MOVIE_STORY WHERE movieCode =:code")
    fun getMovieStory(code: String): Flow<CachedStoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovieStory(cachedStory: CachedStoryEntity)

    @Query("SELECT EXISTS(SELECT * FROM CACHED_MOVIE_STORY WHERE movieCode =:code)")
    fun isPlotExists(code: String): Boolean
}

@Dao
interface BookmarkDao {

    @Query("SELECT EXISTS(SELECT * FROM BOOKMARKED_MOVIE WHERE movieCd =:code)")
    fun isMovieExists(code: String): Flow<Boolean>

    @Query("SELECT * FROM BOOKMARKED_MOVIE")
    fun getAll(): Flow<List<BookmarkedMovie>>

    @Query("SELECT * FROM BOOKMARKED_MOVIE where title like :query ")
    fun getSearchList(query: String): Flow<List<BookmarkedMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(bookmarkedMovie: BookmarkedMovie)

    @Delete
    suspend fun deleteMovie(bookmarkedMovie: BookmarkedMovie)
}