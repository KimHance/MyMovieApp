package com.navermovie.presentation.view.boxoffice

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindPoster")
fun ImageView.bindPoster(uri: String?) {
    uri?.let {
        Glide.with(this).load(it).fitCenter().into(this)
    }
}

@BindingAdapter("bindRank")
fun TextView.bindRank(rank: Int) {
    text = rank.toString()
}