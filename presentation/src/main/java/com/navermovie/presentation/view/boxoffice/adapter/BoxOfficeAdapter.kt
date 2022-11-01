package com.navermovie.presentation.view.boxoffice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.navermovie.entity.Movie
import com.navermovie.presentation.R
import com.navermovie.presentation.view.boxoffice.viewholder.BoxOfficeViewHolder
import com.navermovie.presentation.view.boxoffice.viewholder.ErrorViewHolder
import com.navermovie.presentation.view.boxoffice.viewholder.SkeletonViewHolder

const val UN_FETCHED = 0
const val FETCHED = 1
const val ERROR = 2

class BoxOfficeAdapter(
    private val itemClickListener: (Movie, View) -> Unit
) : ListAdapter<Movie, RecyclerView.ViewHolder>(movieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FETCHED -> {
                BoxOfficeViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_box_office_movie,
                        parent,
                        false
                    ),
                    itemClickListener
                )
            }
            UN_FETCHED -> {
                SkeletonViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_box_office_skeleton,
                        parent,
                        false
                    )
                )
            }
            else -> {
                ErrorViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_box_office_error,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItem(position).isFetched) {
            (holder as BoxOfficeViewHolder).bind(getItem(position))
        } else {
            if (getItem(position).isError) {
                (holder as ErrorViewHolder).bind()
            } else {
                (holder as SkeletonViewHolder).bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isFetched) {
            FETCHED
        } else {
            if (getItem(position).isError) {
                ERROR
            } else {
                UN_FETCHED
            }
        }
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

