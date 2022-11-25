package com.moviechacha.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Movie
import com.moviechacha.presentation.databinding.ItemBoxOfficeMovieBinding

class BoxOfficeViewHolder(
    private val binding: ItemBoxOfficeMovieBinding,
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