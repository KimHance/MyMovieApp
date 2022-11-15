package com.navermovie.data.repositoryimpl

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
            }.onFailure { error ->
                throw error
            }
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
            }.onFailure { error ->
                throw error
            }
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
        val director = movie.directors?.first()?.name
        val prodYear = movie.prodYear
        var isPosterFetched = false
        naverSearchDataSource.getMoviePoster(movie.title).collect { result ->
            runCatching {
                result
            }.onSuccess {
                it?.items?.forEach { item ->
                    val token = item?.director?.split("|")
                    token?.forEach { name ->
                        if ((name == director) and (item.pubDate == prodYear)) {
                            val fetchedMovie = movie.copy(
                                poster = item.image,
                                rating = item.userRating,
                                isFetched = true
                            )
                            isPosterFetched = true
                            emit(fetchedMovie)
                        }
                    }
                }
                if (!isPosterFetched) {
                    emit(movie.copy(isFetched = true))
                }
            }.onFailure { error ->
                throw error
            }
        }
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
        val director = movie.directors?.first()?.name
        val englishName = movie.directors?.first()?.englishName
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
        koficMovieDataSource.getSearchResponse(query).collect { result ->
            emit(
                runCatching {
                    result
                }.mapCatching {
                    it.movieListResult.movieList.toMovie()
                }.getOrThrow()
            )
        }
    }

}