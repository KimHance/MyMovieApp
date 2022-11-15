package com.navermovie.data.remote.response


import com.navermovie.entity.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("movieListResult")
    val movieListResult: MovieListResult
) {
    @Serializable
    data class MovieListResult(
        val movieList: List<SearchMovie>,
    ) {
        @Serializable
        data class SearchMovie(
            val movieCd: String, // 2022A912
            val movieNm: String, // 해리포터와 마법사의 돌: 매지컬 무비 모드
        )
    }
}

fun List<SearchResponse.MovieListResult.SearchMovie>.toMovie(): List<Movie> {
    return this.map {
        Movie(
            movieCd = it.movieCd,
            title = it.movieNm
        )
    }
}