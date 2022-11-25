package com.moviechacha.presentation.view.detail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.presentation.databinding.ItemDetailGenreBinding

class GenreViewHolder(
    private val binding: ItemDetailGenreBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.apply {
            genre = item
            executePendingBindings()
        }
    }
}