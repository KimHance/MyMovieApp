package com.navermovie.data.repositoryimpl

import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.datasource.NaverMovieDataSource
import com.navermovie.data.remote.response.toActorString
import com.navermovie.data.remote.response.toAuditString
import com.navermovie.data.remote.response.toDirectorString
import com.navermovie.data.remote.response.toGenreString
import com.navermovie.di.DispatcherModule
import com.navermovie.entity.Movie
import com.navermovie.repository.RemoteMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteMovieRepositoryImpl @Inject constructor(
    private val koficMovieDataSource: KoficMovieDataSource,
    private val naverMovieDataSource: NaverMovieDataSource,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
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
            naverMovieDataSource.getMoviePoster(movie.title)
        }.mapCatching {
            movie.copy(
                poster = it.items?.first()?.image,
                rating = it.items?.first()?.userRating
            )
        }.getOrThrow()
    }
}