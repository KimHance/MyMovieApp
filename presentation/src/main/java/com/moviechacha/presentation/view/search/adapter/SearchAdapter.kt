package com.moviechacha.presentation.view.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Movie
import com.moviechacha.presentation.R
import com.moviechacha.presentation.view.boxoffice.adapter.FETCHED
import com.moviechacha.presentation.view.boxoffice.adapter.UN_FETCHED
import com.moviechacha.presentation.view.boxoffice.viewholder.BoxOfficeWeekViewHolder
import com.moviechacha.presentation.view.boxoffice.viewholder.WeekSkeletonViewHolder

class SearchAdapter(
    private val itemClickListener: (Movie) -> Unit
) : ListAdapter<Movie, RecyclerView.ViewHolder>(searchMovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FETCHED -> {
                BoxOfficeWeekViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_box_office_week,
                        parent,
                        false
                    ), itemClickListener
                )
            }
            else -> {
                WeekSkeletonViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_box_office_week_skeleton,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).isFetched) {
            true -> {
                (holder as BoxOfficeWeekViewHolder).bind(getItem(position))
            }
            false -> {
                (holder as WeekSkeletonViewHolder).bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isFetched) {
            FETCHED
        } else {
            UN_FETCHED
        }
    }

    companion object {
        private val searchMovieDiffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.movieCd == newItem.movieCd

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}