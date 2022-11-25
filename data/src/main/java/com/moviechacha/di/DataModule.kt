package com.moviechacha.di

import com.moviechacha.data.local.datasource.BookmarkDataSource
import com.moviechacha.data.local.datasource.CachedDataSource
import com.moviechacha.data.local.datasource.impl.BookmarkDataSourceImpl
import com.moviechacha.data.local.datasource.impl.CachedDataSourceImpl
import com.moviechacha.data.remote.datasource.*
import com.moviechacha.data.remote.datasource.impl.*
import com.moviechacha.data.repositoryimpl.MovieRepositoryImpl
import com.moviechacha.data.repositoryimpl.RemoteMovieRepositoryImpl
import com.moviechacha.repository.MovieRepository
import com.moviechacha.repository.RemoteMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindKoficDataSource(
        impl: KoficMovieDataSourceImpl
    ): KoficMovieDataSource

    @Binds
    @Singleton
    abstract fun bindNaverDataSource(
        impl: NaverSearchDataSourceImpl
    ): NaverSearchDataSource

    @Binds
    @Singleton
    abstract fun bindYoutubeDataSource(
        impl: YoutubeDataSourceImpl
    ): YoutubeDataSource

    @Binds
    @Singleton
    abstract fun bindKakaoDatSource(
        impl: KakaoSearchDataSourceImpl
    ): KakaoSearchDataSource

    @Binds
    @Singleton
    abstract fun bindKmdbDataSource(
        impl: KmdbSearchDataSourceImpl
    ): KmdbSearchDataSource

    @Binds
    @Singleton
    abstract fun bindCachedDataSource(
        impl: CachedDataSourceImpl
    ): CachedDataSource

    @Binds
    @Singleton
    abstract fun bindBookmarkDataSource(
        impl: BookmarkDataSourceImpl
    ): BookmarkDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteMovieRepository(
        impl: RemoteMovieRepositoryImpl
    ): RemoteMovieRepository

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository
}