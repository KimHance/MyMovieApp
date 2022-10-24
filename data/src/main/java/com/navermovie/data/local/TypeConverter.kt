package com.navermovie.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.navermovie.entity.Actor
import com.navermovie.entity.Article

class ActorTypeConverter {

    @TypeConverter
    fun fromActorList(value: List<Actor>) = Gson().toJson(value)

    @TypeConverter
    fun toActorList(value: String) = Gson().fromJson(value, Array<Actor>::class.java).toList()
}

class ArticleTypeConverter {

    @TypeConverter
    fun fromArticleList(value: List<Article>) = Gson().toJson(value)

    @TypeConverter
    fun toArticleList(value: String) = Gson().fromJson(value, Array<Article>::class.java).toList()
}