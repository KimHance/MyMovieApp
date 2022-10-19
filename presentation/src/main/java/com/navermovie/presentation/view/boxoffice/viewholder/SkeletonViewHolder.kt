package com.navermovie.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navermovie.presentation.databinding.ItemBoxOfficeSkeletonBinding

class SkeletonViewHolder(
    private val binding: ItemBoxOfficeSkeletonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.executePendingBindings()
    }
}