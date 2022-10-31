package com.navermovie.data.repositoryimpl

import com.navermovie.PLOT_ERROR
import com.navermovie.data.remote.datasource.*
import com.navermovie.data.remote.response.toActorString
import com.navermovie.data.remote.response.toAuditString
import com.navermovie.data.remote.response.toDirectorString
import com.navermovie.data.remote.response.toGenreString
import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.entity.Movie
import com.navermovie.repository.RemoteMovieRepository
import javax.inject.Inject

class RemoteMovieRepositoryImpl @Inject constructor(
    private val koficMovieDataSource: KoficMovieDataSource,
    private val naverSearchDataSource: NaverSearchDataSource,
    private val youtubeDataSource: YoutubeDataSource,
    private val kakaoSearchDataSource: KakaoSearchDataSource,
    private val kmdbSearchDataSource: KmdbSearchDataSource
) : RemoteMovieRepository {

    override suspend fun getMovieList(): List<Movie>? {
        return runCatching {
            koficMovieDataSource.getDailyBoxOfficeList()
        }.mapCatching { result ->
            result.boxOfficeResult?.dailyBoxOfficeList?.map { boxOffice ->
                Movie(
                    title = boxOffice.movieNm,
                    rank = boxOffice.rank.toInt(),
                    rankType = boxOffice.rankOldAndNew,
                    movieCd = boxOffice.movieCd,
                )
            }
        }.getOrThrow()
    }

    override suspend fun fetchMovieDetail(movie: Movie): Movie {
        return runCatching {
            koficMovieDataSource.getMovieDetail(movie.movieCd)
        }.mapCatching {
            with(it.movieInfoResult?.movieInfo) {
                movie.copy(
                    openDate = this?.openDt,
                    actors = this?.actors.toActorString(),
                    genres = this?.genres.toGenreString(),
                    audits = this?.audits.toAuditString(),
                    directors = this?.directors.toDirectorString(),
                    showTime = this?.showTm
                )
            }
        }.getOrThrow()
    }

    override suspend fun fetchMoviePoster(movie: Movie): Movie {
        return runCatching {
            naverSearchDataSource.getMoviePoster(movie.title)
        }.mapCatching {
            movie.copy(
                poster = it.items?.first()?.image,
                rating = it.items?.first()?.userRating,
                isFetched = true
            )
        }.getOrThrow()
    }

    override suspend fun getMovieTeaser(query: String): String? {
        return runCatching {
            youtubeDataSource.getMovieTeaserLink(query)
        }.mapCatching {
            it.items?.first()?.id?.videoId
        }.getOrThrow()
    }

    override suspend fun getImageUrl(movie: Movie): List<Actor>? {
        val title = movie.title
        return movie.actors?.map { actor ->
            val query = "$title $actor"
            runCatching {
                kakaoSearchDataSource.getImage(query)
            }.mapCatching { response ->
                Actor(actor, response.documents?.first()?.image_url, true)
            }.getOrDefault(
                Actor(actor, isFetched = true)
            )
        }
    }

    override suspend fun getMovieArticle(movie: Movie): List<Article>? {
        return runCatching {
            naverSearchDataSource.getMovieArticle(movie.title)
        }.mapCatching { response ->
            response.items?.map { item ->
                Article(
                    link = item?.originallink,
                    title = item?.title,
                    description = item?.description,
                    isFetched = true
                )
            }
        }.getOrNull()
    }

    override suspend fun getMoviePlot(movie: Movie): String {
        val director = movie.directors?.first()
        return runCatching {
            kmdbSearchDataSource.getMoviePlot(movie.title)
        }.mapCatching {
            var plot = PLOT_ERROR
            it?.data?.first()?.result?.forEach { result ->
                result?.directors?.director?.forEach { directors ->
                    if (directors?.directorNm == director) {
                        plot = result.plots?.plot?.first()?.plotText.toString()
                    }
                }
            }
            plot
        }.getOrDefault(PLOT_ERROR)
    }
}