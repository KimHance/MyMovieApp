package com.moviechacha.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.presentation.databinding.ItemBoxOfficeWeekSkeletonBinding

class WeekSkeletonViewHolder(
    private val binding: ItemBoxOfficeWeekSkeletonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.executePendingBindings()
    }
}