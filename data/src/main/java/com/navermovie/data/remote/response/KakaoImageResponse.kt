package com.navermovie.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoImageResponse(
    @SerialName("documents")
    val documents: List<Document?>?,
) {
    @Serializable
    data class Document(
        val imageUrl: String?, // http://img.insight.co.kr/static/2017/07/10/300/5bu7p43u87xi7z8j627v.jpg
    )
}