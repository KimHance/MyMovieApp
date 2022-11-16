package com.navermovie.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.navermovie.data.local.DirectorTypeConverter
import com.navermovie.data.local.StringTypeConverter
import com.navermovie.data.local.dao.BookmarkDao
import com.navermovie.data.local.dto.BookmarkedMovie

@Database(
    entities = [BookmarkedMovie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DirectorTypeConverter::class,
    StringTypeConverter::class
)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun BookmarkDao(): BookmarkDao

    companion object {
        fun getInstance(context: Context): BookmarkDatabase = Room
            .databaseBuilder(context, BookmarkDatabase::class.java, "bookmark.db")
            .build()
    }
}
