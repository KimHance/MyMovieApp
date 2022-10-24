package com.navermovie.di

import com.navermovie.data.remote.service.KakaoSearchService
import com.navermovie.data.remote.service.KoficMovieService
import com.navermovie.data.remote.service.NaverSearchService
import com.navermovie.data.remote.service.YoutubeService
import com.navermovie.utils.KakaoAuthInterceptor
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

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class KoficRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class YoutubeRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class KakaoRetrofit

    // for Retrofit
    @Provides
    @Singleton
    @KoficRetrofit
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
    @YoutubeRetrofit
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
    @KakaoRetrofit
    fun provideKakaoRetrofit(
        @KakaoRetrofit okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    // for httpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
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
    @KakaoRetrofit
    fun provideKakaoHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(KakaoAuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    // for Service
    @Provides
    @Singleton
    fun provideNaverSearchService(
        @NaverRetrofit retrofit: Retrofit
    ): NaverSearchService = retrofit.create(NaverSearchService::class.java)

    @Provides
    @Singleton
    fun provideKoficMovieService(
        @KoficRetrofit retrofit: Retrofit
    ): KoficMovieService = retrofit.create(KoficMovieService::class.java)

    @Provides
    @Singleton
    fun provideYoutubeService(
        @YoutubeRetrofit retrofit: Retrofit
    ): YoutubeService = retrofit.create(YoutubeService::class.java)

    @Provides
    @Singleton
    fun provideKakaoService(
        @KakaoRetrofit retrofit: Retrofit
    ): KakaoSearchService = retrofit.create(KakaoSearchService::class.java)

    companion object {
        const val NAVER_BASE_URL = "https://openapi.naver.com/v1/search/"
        const val KOFIC_BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/"
        const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
        const val KAKAO_BASE_URL = "https://dapi.kakao.com/"
    }
}