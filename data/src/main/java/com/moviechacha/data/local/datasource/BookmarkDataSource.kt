package com.moviechacha.data.local.datasource

import com.moviechacha.data.local.dto.BookmarkedMovie
import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {
    fun getAll(): Flow<List<BookmarkedMovie>>
    fun getSearchList(query: String): Flow<List<BookmarkedMovie>>
}