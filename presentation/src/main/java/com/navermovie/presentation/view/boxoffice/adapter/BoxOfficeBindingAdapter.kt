package com.navermovie.presentation.view.boxoffice.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.navermovie.presentation.R

@BindingAdapter("bindPoster")
fun ImageView.bindPoster(uri: String?) {
    if (uri.isNullOrBlank()) {
        Glide.with(this).load(R.drawable.ic_empty_image).into(this)
    } else {
        Glide.with(this).load(uri).override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
            .into(this)
    }
}

@BindingAdapter("bindRank")
fun TextView.bindRank(rank: Int) {
    text = rank.toString()
}