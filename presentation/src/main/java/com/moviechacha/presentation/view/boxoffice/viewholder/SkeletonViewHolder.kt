package com.moviechacha.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.presentation.databinding.ItemBoxOfficeSkeletonBinding

class SkeletonViewHolder(
    private val binding: ItemBoxOfficeSkeletonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.executePendingBindings()
    }
}