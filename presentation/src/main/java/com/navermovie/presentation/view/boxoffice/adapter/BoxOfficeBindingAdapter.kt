package com.navermovie.presentation.view.boxoffice.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat

@BindingAdapter("bindPoster")
fun ImageView.bindPoster(uri: String?) {
    uri?.let {
        Glide.with(this).load(it).format(DecodeFormat.PREFER_ARGB_8888).into(this)
    }
}

@BindingAdapter("bindRank")
fun TextView.bindRank(rank: Int) {
    text = rank.toString()
}