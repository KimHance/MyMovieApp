package com.navermovie.data.local.datasource.impl

import com.navermovie.data.local.dao.BookmarkDao
import com.navermovie.data.local.datasource.BookmarkDataSource
import com.navermovie.data.local.dto.BookmarkedMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkDataSource {
    override fun getAll(): Flow<List<BookmarkedMovie>> =
        bookmarkDao.getAll()

    override fun getSearchList(query: String): Flow<List<BookmarkedMovie>> =
        bookmarkDao.getSearchList(query)
}