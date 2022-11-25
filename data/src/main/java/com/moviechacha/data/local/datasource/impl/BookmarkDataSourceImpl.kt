package com.moviechacha.data.local.datasource.impl

import com.moviechacha.data.local.dao.BookmarkDao
import com.moviechacha.data.local.datasource.BookmarkDataSource
import com.moviechacha.data.local.dto.BookmarkedMovie
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