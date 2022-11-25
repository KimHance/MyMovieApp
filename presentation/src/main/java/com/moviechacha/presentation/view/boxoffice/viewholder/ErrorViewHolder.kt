package com.moviechacha.presentation.view.boxoffice.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moviechacha.presentation.databinding.ItemBoxOfficeErrorBinding

class ErrorViewHolder(
    private val binding: ItemBoxOfficeErrorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.executePendingBindings()
    }
}