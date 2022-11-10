package com.navermovie.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.navermovie.data.remote.datasource.KoficMovieDataSource
import com.navermovie.data.remote.response.SearchResponse

const val STARTING_KEY = 1

class SearchPagingSource(
    private val koficMovieDataSource: KoficMovieDataSource,
    private val query: String
) : PagingSource<Int, SearchResponse.MovieListResult.SearchMovie>() {

    override fun getRefreshKey(state: PagingState<Int, SearchResponse.MovieListResult.SearchMovie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResponse.MovieListResult.SearchMovie> {
        val position = params.key ?: STARTING_KEY
        return try {
            val movieList = koficMovieDataSource.getSearchResponse(query).movieListResult.movieList
            LoadResult.Page(
                data = movieList,
                prevKey = if (position == STARTING_KEY) null else position - 1,
                nextKey = if (movieList.isEmpty()) null else position + 1
            )
        } catch (e: Throwable) {
            return LoadResult.Error(e)
        }
    }
}