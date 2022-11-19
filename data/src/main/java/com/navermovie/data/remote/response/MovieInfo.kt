package com.navermovie.data.remote.response

import com.navermovie.entity.Directors
import kotlinx.serialization.Serializable

@Serializable
data class KoficMovieInfoResult(
    val movieInfoResult: MovieInfoResult?
)

@Serializable
data class MovieInfoResult(
    val movieInfo: MovieInfo?
)

@Serializable
data class MovieInfo(
    val openDt: String?,
    val prdtYear: String?,
    val actors: List<Actor?>?,
    val audits: List<Audit?>?,
    val directors: List<Director?>?,
    val genres: List<Genre?>?,
    val showTm: String? // 상영 시간(분)
)

@Serializable
data class Audit(
    val watchGradeNm: String // 15세이상관람가
)

@Serializable
data class Director(
    val peopleNm: String, // 추창민
    val peopleNmEn: String
)

@Serializable
data class Genre(
    val genreNm: String // 사극
)

@Serializable
data class Actor(
    val peopleNm: String, // 이병헌
)

fun List<Actor?>?.toActorString(): List<String>? {
    return this?.map {
        it?.peopleNm.toString()
    }
}

fun List<Audit?>?.toAuditString(): List<String>? {
    return this?.map {
        it?.watchGradeNm.toString()
    }
}

fun List<Director?>?.toDirectorString(): List<Directors> {
    return this?.map {
        Directors(
            name = it?.peopleNm.toString(),
            englishName = it?.peopleNmEn.toString()
        )

    }!!
}

fun List<Genre?>?.toGenreString(): List<String>? {
    return this?.map {
        it?.genreNm.toString()
    }
}