package com.navermovie.presentation.view.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.navermovie.entity.Movie
import com.navermovie.presentation.R
import com.navermovie.presentation.view.bookmark.viewholder.BookmarkViewHolder

class BookmarkAdapter(
    private val itemClickListener: (Movie) -> Unit
) : ListAdapter<Movie, BookmarkViewHolder>(bookmarkDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_book_mark_movie,
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val bookmarkDiffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.movieCd == newItem.movieCd

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }

}