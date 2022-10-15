package com.navermovie.di

import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.datasource.NaverMovieDataSource
import com.navermovie.data.remote.datasource.impl.KoficMovieDataSourceImpl
import com.navermovie.data.remote.datasource.impl.NaverMovieDataSourceImpl
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
        impl: NaverMovieDataSourceImpl
    ): NaverMovieDataSource

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