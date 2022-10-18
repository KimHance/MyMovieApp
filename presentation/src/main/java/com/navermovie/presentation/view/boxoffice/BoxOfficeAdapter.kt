package com.navermovie.presentation.view.boxoffice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.navermovie.entity.Movie
import com.navermovie.presentation.R

class BoxOfficeAdapter(
    private val itemClickListener: (Movie) -> Unit
) : ListAdapter<Movie, BoxOfficeViewHolder>(movieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeViewHolder {
        return BoxOfficeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_box_office_movie,
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: BoxOfficeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val movieDiffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.movieCd == newItem.movieCd

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }
}

