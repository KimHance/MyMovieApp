package com.navermovie.data.remote.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoImageResponse(
    @SerializedName("documents")
    val documents: List<Document?>?,
) {
    @Serializable
    data class Document(
        val image_url: String?, // http://img.insight.co.kr/static/2017/07/10/300/5bu7p43u87xi7z8j627v.jpg
    )
}