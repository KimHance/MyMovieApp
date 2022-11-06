package com.navermovie.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navermovie.entity.Movie
import com.navermovie.presentation.databinding.ItemBoxOfficeWeekBinding
import com.navermovie.presentation.view.detail.adapter.DetailGenreAdapter

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