package com.navermovie.di

import com.navermovie.data.local.datasource.CachedDataSource
import com.navermovie.data.local.datasource.impl.CachedDataSourceImpl
import com.navermovie.data.remote.datasource.KakaoSearchDataSource
import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.datasource.NaverSearchDataSource
import com.navermovie.data.remote.datasource.YoutubeDataSource
import com.navermovie.data.remote.datasource.impl.KakaoSearchDataSourceImpl
import com.navermovie.data.remote.datasource.impl.KoficMovieDataSourceImpl
import com.navermovie.data.remote.datasource.impl.NaverSearchDataSourceImpl
import com.navermovie.data.remote.datasource.impl.YoutubeDataSourceImpl
import com.navermovie.data.repositoryimpl.LocalMovieRepositoryImpl
import com.navermovie.data.repositoryimpl.RemoteMovieRepositoryImpl
import com.navermovie.repository.LocalMovieRepository
import com.navermovie.repository.RemoteMovieRepository
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
    abstract fun bindCachedDataSource(
        impl: CachedDataSourceImpl
    ): CachedDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteMovieRepository(
        impl: RemoteMovieRepositoryImpl
    ): RemoteMovieRepository

    @Binds
    @Singleton
    abstract fun bindLocalMovieRepository(
        impl: LocalMovieRepositoryImpl
    ): LocalMovieRepository
}