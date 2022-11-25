package com.moviechacha.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.moviechacha.data.local.DirectorTypeConverter
import com.moviechacha.data.local.StringTypeConverter
import com.moviechacha.entity.Directors
import com.moviechacha.entity.Movie

@Entity(tableName = "bookmarked_movie")
data class BookmarkedMovie(
    @PrimaryKey
    val movieCd: String = "",
    val title: String = "",
    val rank: Int = 0,
    val rankType: String? = "",
    val openDate: String? = "",
    val prodYear: String? = "",
    @TypeConverters(StringTypeConverter::class)
    val actors: List<String>? = emptyList(),
    @TypeConverters(StringTypeConverter::class)
    val genres: List<String>? = emptyList(),
    @TypeConverters(StringTypeConverter::class)
    val audits: List<String>? = emptyList(),
    @TypeConverters(DirectorTypeConverter::class)
    val directors: List<Directors>? = emptyList(),
    val showTime: String? = "",
    val poster: String? = "",
    val rating: String? = "",
)
fun List<BookmarkedMovie>.toMovieList(): List<Movie> {
    return this.map {
        Movie(
            it.title,
            it.rank,
            it.rankType,
            it.movieCd,
            it.openDate,
            it.prodYear,
            it.actors,
            it.genres,
            it.audits,
            it.directors!!,
            it.showTime,
            it.poster,
            it.rating,
            isFetched = true
        )
    }
}

fun Movie.toBookmark(): BookmarkedMovie {
    return BookmarkedMovie(
        movieCd,
        title,
        rank,
        rankType,
        openDate,
        prodYear,
        actors,
        genres,
        audits,
        directors,
        showTime,
        poster,
        rating
    )
}