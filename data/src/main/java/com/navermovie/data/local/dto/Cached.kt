package com.navermovie.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.navermovie.entity.Actor
import com.navermovie.entity.Article

@Entity(tableName = "cached_actor_image_table")
data class CachedActorImageEntity(
    @PrimaryKey
    val movieCode: String,
    val actorList: List<Actor>,
    val date: Long
)

@Entity(tableName = "cached_article_table")
data class CachedArticleEntity(
    @PrimaryKey
    val movieCode: String,
    val articleList: List<Article>,
    val date: Long
)

@Entity(tableName = "cached_movie_story")
data class CachedStoryEntity(
    @PrimaryKey
    val movieCode: String,
    val movieStory: String,
    val date: Long
)