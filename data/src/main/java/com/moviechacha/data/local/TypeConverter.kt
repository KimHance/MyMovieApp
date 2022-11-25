package com.moviechacha.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moviechacha.entity.Actor
import com.moviechacha.entity.Article
import com.moviechacha.entity.Directors

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

@ProvidedTypeConverter
class DirectorTypeConverter {

    @TypeConverter
    fun fromDirectorList(value: List<Directors>?) = Gson().toJson(value) ?: null

    @TypeConverter
    fun toDirectorList(value: String?): List<Directors>? {
        return if (value != null) {
            Gson().fromJson(value, Array<Directors>::class.java).toList()
        } else {
            null
        }
    }

}

@ProvidedTypeConverter
class StringTypeConverter {
    @TypeConverter
    fun fromString(value: List<String>?) = Gson().toJson(value) ?: null

    @TypeConverter
    fun toString(value: String?): List<String>? {
        return if (value != null) {
            Gson().fromJson(value, Array<String>::class.java).toList()
        } else {
            null
        }
    }

}