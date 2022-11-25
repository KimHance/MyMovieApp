package com.moviechacha.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Movie
import com.moviechacha.presentation.databinding.ItemBoxOfficeWeekBinding
import com.moviechacha.presentation.view.detail.adapter.DetailGenreAdapter

class BoxOfficeWeekViewHolder(
    private val binding: ItemBoxOfficeWeekBinding,
    private val itemClickListener: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val genreAdapter: DetailGenreAdapter by lazy {
        DetailGenreAdapter()
    }

    fun bind(item: Movie) {
        binding.apply {
            movie = item
            executePendingBindings()
            itemView.setOnClickListener {
                itemClickListener(item)
            }
            rvBoxOfficeWeekGenre.adapter = genreAdapter
            genreAdapter.submitList(item.genres?.toList())
        }
    }
}