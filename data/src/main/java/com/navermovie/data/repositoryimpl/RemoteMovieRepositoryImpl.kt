package com.navermovie.data.repositoryimpl

import androidx.core.text.HtmlCompat
import com.navermovie.EMPTY_VALUE
import com.navermovie.PLOT_ERROR
import com.navermovie.data.remote.datasource.*
import com.navermovie.data.remote.response.*
import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.entity.Movie
import com.navermovie.repository.RemoteMovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RemoteMovieRepositoryImpl @Inject constructor(
    private val koficMovieDataSource: KoficMovieDataSource,
    private val naverSearchDataSource: NaverSearchDataSource,
    private val youtubeDataSource: YoutubeDataSource,
    private val kakaoSearchDataSource: KakaoSearchDataSource,
    private val kmdbSearchDataSource: KmdbSearchDataSource
) : RemoteMovieRepository {

    override fun getDailyMovieList() = flow {
        koficMovieDataSource.getDailyBoxOfficeList().collect { result ->
            runCatching {
                result
            }.onSuccess { list ->
                list?.forEach { boxOffice ->
                    emit(
                        Movie(
                            title = boxOffice.movieNm,
                            rank = boxOffice.rank.toInt(),
                            rankType = boxOffice.rankOldAndNew,
                            movieCd = boxOffice.movieCd,
                        )
                    )
                }
            }.getOrThrow()
        }
    }

    override fun getWeeklyMovieList() = flow {
        koficMovieDataSource.getWeeklyBoxOfficeList().collect { result ->
            runCatching {
                result
            }.onSuccess { list ->
                list?.forEach { boxOffice ->
                    emit(
                        Movie(
                            title = boxOffice.movieNm,
                            rank = boxOffice.rank.toInt(),
                            rankType = boxOffice.rankOldAndNew,
                            movieCd = boxOffice.movieCd,
                        )
                    )
                }
            }.getOrThrow()
        }
    }

    override fun fetchMovieDetail(movie: Movie) = flow {
        koficMovieDataSource.getMovieDetail(movie.movieCd).collect { result ->
            emit(
                runCatching {
                    result
                }.mapCatching {
                    with(it?.movieInfoResult?.movieInfo) {
                        movie.copy(
                            openDate = this?.openDt,
                            prodYear = this?.prdtYear,
                            actors = this?.actors.toActorString(),
                            genres = this?.genres.toGenreString(),
                            audits = this?.audits.toAuditString(),
                            directors = this?.directors.toDirectorString(),
                            showTime = this?.showTm
                        )
                    }
                }.getOrThrow()
            )
        }
    }

    override fun fetchMoviePoster(movie: Movie) = flow {
        val prodYear = movie.prodYear ?: EMPTY_VALUE
        var isPosterFetched = false
        val movieTitle = movie.title.replace(" ", "")
        naverSearchDataSource.getMoviePoster(movie.title).collect { response ->
            runCatching {
                response
            }.onSuccess { result ->
                result?.items?.forEach { item ->
                    item?.let {
                        val title = HtmlCompat.fromHtml(it.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
                            .toString().replace(" ", "")
                        if ((movieTitle == title) and (item.pubDate == prodYear)) {
                            addPoster(movie, item)
                            isPosterFetched = true
                        }
                    }

                }
                if (!isPosterFetched) {
                    emit(movie.copy(isFetched = true))
                }
            }.onFailure {
                emit(movie.copy(isFetched = true))
            }
        }
    }

    private suspend fun FlowCollector<Movie>.addPoster(
        movie: Movie,
        item: Item
    ) {
        val fetchedMovie = movie.copy(
            poster = item.image,
            rating = item.userRating,
            isFetched = true
        )
        emit(fetchedMovie)
    }

    override fun getMovieTeaser(query: String) = flow {
        youtubeDataSource.getMovieTeaserLink(query).collect { result ->
            emit(
                runCatching {
                    result
                }.mapCatching {
                    it?.items?.first()?.id?.videoId
                }.getOrNull()
            )
        }
    }

    override suspend fun getImageUrl(movie: Movie): Flow<List<Actor>> {
        val title = movie.title
        return flowOf(movie.actors?.asFlow()?.map { actor ->
            val query = "$title $actor"
            runCatching {
                kakaoSearchDataSource.getImage(query)
            }.mapCatching { result ->
                Actor(actor, result.documents?.first()?.image_url, true)
            }.getOrDefault(
                Actor(actor, isFetched = true)
            )
        }?.toList() ?: emptyList())
    }

    override fun getMovieArticle(movie: Movie) = flow {
        naverSearchDataSource.getMovieArticle(movie.title).collect { result ->
            emit(
                runCatching {
                    result
                }.mapCatching { response ->
                    response?.items?.map { item ->
                        Article(
                            link = item?.originallink,
                            title = item?.title,
                            description = item?.description,
                            isFetched = true
                        )
                    }
                }.getOrNull()
            )
        }
    }

    override fun getMoviePlot(movie: Movie) = flow {
        val director = movie.directors?.first()?.name ?: EMPTY_VALUE
        val englishName = movie.directors?.first()?.englishName ?: EMPTY_VALUE
        kmdbSearchDataSource.getMoviePlot(movie.title).collect { result ->
            emit(
                runCatching {
                    result
                }.mapCatching { response ->
                    var plot = PLOT_ERROR
                    response?.data?.first()?.result?.forEach { result ->
                        result?.directors?.director?.forEach { directors ->
                            if ((directors?.directorNm == director) or (directors?.directorEnNm == englishName)) {
                                plot = result.plots?.plot?.first()?.plotText.toString()
                            }
                        }
                    }
                    plot
                }.getOrDefault(PLOT_ERROR)
            )
        }
    }

    override fun getSearchList(query: String) = flow {
        koficMovieDataSource.getSearchResponse(query).collect { response ->
            emit(
                runCatching {
                    response
                }.mapCatching { result ->
                    result.movieListResult.movieList.filter {
                        it.prdtStatNm == "개봉"
                    }.toMovie()
                }.getOrThrow()
            )
        }
    }

}