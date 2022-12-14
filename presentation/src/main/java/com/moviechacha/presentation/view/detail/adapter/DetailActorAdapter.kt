package com.moviechacha.presentation.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.entity.Actor
import com.moviechacha.presentation.R
import com.moviechacha.presentation.view.boxoffice.adapter.FETCHED
import com.moviechacha.presentation.view.boxoffice.adapter.UN_FETCHED
import com.moviechacha.presentation.view.detail.viewholder.ActorSkeletonViewHolder
import com.moviechacha.presentation.view.detail.viewholder.ActorViewHolder

class DetailActorAdapter : ListAdapter<Actor, RecyclerView.ViewHolder>(actorDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FETCHED -> {
                ActorViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_detail_actor,
                        parent,
                        false
                    )
                )
            }
            else -> {
                ActorSkeletonViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_detail_actor_skeleton,
                        parent,
                        false
                    )
                )
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).isFetched) {
            true -> {
                (holder as ActorViewHolder).bind(getItem(position))
            }
            false -> {
                (holder as ActorSkeletonViewHolder).bind()
            }
        }
    }

    companion object {
        val actorDiffUtil = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem == newItem

        }
    }
}