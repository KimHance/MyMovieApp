package com.moviechacha.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.presentation.databinding.ItemDetailActorSkeletonBinding

class ActorSkeletonViewHolder(
    private val binding: ItemDetailActorSkeletonBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.executePendingBindings()
    }
}