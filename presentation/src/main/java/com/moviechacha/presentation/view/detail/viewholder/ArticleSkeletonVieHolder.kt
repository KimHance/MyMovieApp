package com.moviechacha.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.presentation.databinding.ItemDetailArticleSkeletonBinding

class ArticleSkeletonVieHolder(
    private val binding: ItemDetailArticleSkeletonBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.executePendingBindings()
    }
}