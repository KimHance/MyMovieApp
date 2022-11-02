package com.navermovie.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.navermovie.presentation.databinding.ItemBoxOfficeErrorBinding

class ErrorViewHolder(
    private val binding: ItemBoxOfficeErrorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.executePendingBindings()
    }
}