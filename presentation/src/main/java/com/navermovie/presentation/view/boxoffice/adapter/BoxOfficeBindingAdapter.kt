package com.navermovie.presentation.view.boxoffice.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindPoster")
fun ImageView.bindPoster(uri: String?) {
    uri?.let {
        Glide.with(this).load(it).override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
            .into(this)
    }
}

@BindingAdapter("bindRank")
fun TextView.bindRank(rank: Int) {
    text = rank.toString()
}