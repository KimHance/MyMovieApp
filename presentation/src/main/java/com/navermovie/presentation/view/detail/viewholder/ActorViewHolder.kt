package com.navermovie.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navermovie.entity.Actor
import com.navermovie.presentation.databinding.ItemDetailActorBinding

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