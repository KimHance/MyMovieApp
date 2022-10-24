package com.navermovie.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navermovie.presentation.databinding.ItemDetailArticleSkeletonBinding

class ArticleSkeletonVieHolder(
    private val binding: ItemDetailArticleSkeletonBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.executePendingBindings()
    }
}