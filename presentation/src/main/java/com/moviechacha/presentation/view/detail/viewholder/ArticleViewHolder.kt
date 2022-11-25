package com.moviechacha.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Article
import com.moviechacha.presentation.databinding.ItemDetailArticleBinding

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