package com.navermovie.utils

import com.navermovie.KAKAO_KEY
import com.navermovie.NAVER_ID_KEY
import com.navermovie.NAVER_SECRET_KEY
import okhttp3.Interceptor

class NaverAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        chain.proceed(
            chain.request().newBuilder().apply {
                header("X-Naver-Client-Id", NAVER_ID_KEY)
                addHeader("X-Naver-Client-Secret", NAVER_SECRET_KEY).build()
            }.build()
        )
}

class KakaoAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        chain.proceed(
            chain.request().newBuilder().apply {
                header("Authorization: KakaoAK ", KAKAO_KEY).build()
            }.build()
        )
}