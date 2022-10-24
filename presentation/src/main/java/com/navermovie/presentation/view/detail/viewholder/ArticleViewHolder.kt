package com.navermovie.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navermovie.entity.Article
import com.navermovie.presentation.databinding.ItemDetailArticleBinding

class ArticleViewHolder(
    private val binding: ItemDetailArticleBinding,
    private val itemClickListener: (Article) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article) {
        binding.apply {
            article = item
            itemView.setOnClickListener {
                itemClickListener(item)
            }
            executePendingBindings()
        }
    }
}