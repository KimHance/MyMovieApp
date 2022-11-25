package com.moviechacha.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Actor
import com.moviechacha.presentation.databinding.ItemDetailActorBinding

class ActorViewHolder(
    private val binding: ItemDetailActorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Actor) {
        binding.apply {
            actor = item
            executePendingBindings()
        }
    }
}