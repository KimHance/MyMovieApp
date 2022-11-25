package com.moviechacha.presentation.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Article
import com.moviechacha.presentation.R
import com.moviechacha.presentation.view.boxoffice.adapter.FETCHED
import com.moviechacha.presentation.view.boxoffice.adapter.UN_FETCHED
import com.moviechacha.presentation.view.detail.viewholder.ArticleSkeletonVieHolder
import com.moviechacha.presentation.view.detail.viewholder.ArticleViewHolder

class DetailArticleAdapter(
    private val itemClickListener: (Article) -> Unit
) : ListAdapter<Article, RecyclerView.ViewHolder>(articleDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FETCHED -> {
                ArticleViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_detail_article,
                        parent,
                        false
                    ),
                    itemClickListener
                )
            }
            else -> {
                ArticleSkeletonVieHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_detail_article_skeleton,
                        parent,
                        false
                    )
                )
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isFetched) {
            FETCHED
        } else {
            UN_FETCHED
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).isFetched) {
            true -> {
                (holder as ArticleViewHolder).bind(getItem(position))
            }
            false -> {
                (holder as ArticleSkeletonVieHolder).bind()
            }
        }
    }

    companion object {
        val articleDiffUtil = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.link == newItem.link

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

        }
    }
}