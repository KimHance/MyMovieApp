package com.navermovie.di

import com.navermovie.data.remote.service.KoficMovieService
import com.navermovie.data.remote.service.NaverMovieService
import com.navermovie.data.remote.service.YoutubeService
import com.navermovie.utils.NaverAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NaverRetrofit

    @Provides
    @Singleton
    fun provideKoficRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KOFIC_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideYoutubeRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @NaverRetrofit
    fun provideNaverRetrofit(
        @NaverRetrofit okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NAVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideKoficHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @NaverRetrofit
    fun provideNaverHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(NaverAuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideNaverMovieService(
        @NaverRetrofit retrofit: Retrofit
    ): NaverMovieService = retrofit.create(NaverMovieService::class.java)

    @Provides
    @Singleton
    fun provideKoficMovieService(
        retrofit: Retrofit
    ): KoficMovieService = retrofit.create(KoficMovieService::class.java)

    @Provides
    @Singleton
    fun provideYoutubeService(
        retrofit: Retrofit
    ): YoutubeService = retrofit.create(YoutubeService::class.java)

    companion object {
        const val NAVER_BASE_URL = "https://openapi.naver.com/v1/search/"
        const val KOFIC_BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/"
        const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
    }
}