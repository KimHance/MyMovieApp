package com.moviechacha.di

import android.content.Context
import com.moviechacha.data.local.database.BookmarkDatabase
import com.moviechacha.data.local.database.CachedActorDatabase
import com.moviechacha.data.local.database.CachedArticleDatabase
import com.moviechacha.data.local.database.CachedStoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCachedActorDao(database: CachedActorDatabase) = database.CachedActorDao()

    @Provides
    @Singleton
    fun provideCachedArticleDao(database: CachedArticleDatabase) = database.CachedArticleDao()

    @Provides
    @Singleton
    fun provideCachedStoryDao(database: CachedStoryDatabase) = database.CachedStoryDao()

    @Provides
    @Singleton
    fun provideBookmarkDao(database: BookmarkDatabase) = database.BookmarkDao()

    @Provides
    @Singleton
    fun provideCachedActorDatabase(
        @ApplicationContext context: Context
    ): CachedActorDatabase = CachedActorDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCachedArticleDatabase(
        @ApplicationContext context: Context
    ): CachedArticleDatabase = CachedArticleDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCachedStoryDatabase(
        @ApplicationContext context: Context
    ): CachedStoryDatabase = CachedStoryDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideBookmarkDatabase(
        @ApplicationContext context: Context
    ): BookmarkDatabase = BookmarkDatabase.getInstance(context)
}