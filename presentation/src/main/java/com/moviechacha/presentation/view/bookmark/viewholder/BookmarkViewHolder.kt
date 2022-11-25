package com.moviechacha.presentation.view.bookmark.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Movie
import com.moviechacha.presentation.databinding.ItemBookMarkMovieBinding

class BookmarkViewHolder(
    private val binding: ItemBookMarkMovieBinding,
    private val itemClickListener: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.apply {
            movie = item
            itemView.setOnClickListener {
                itemClickListener(item)
            }
            executePendingBindings()
        }
    }
}