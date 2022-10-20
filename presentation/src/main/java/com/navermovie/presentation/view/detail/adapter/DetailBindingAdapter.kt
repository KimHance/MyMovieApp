package com.navermovie.presentation.view.detail.adapter

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindOpenDate")
fun TextView.bindOpenDate(date: String) {
    val year = date.subSequence(0, 4)
    text = year
}

@BindingAdapter("bindShowTime")
fun TextView.bindShowTime(time: String) {
    val hours = time.toInt() / 60
    val minutes = time.toInt() % 60
    text = "${hours}h ${minutes}m"
}

@BindingAdapter("bindRating")
fun TextView.bindRating(rating: String) {
    val rate = rating.toFloat() / 2
    setText(String.format("%.1f", rate))
}

@BindingAdapter("bindRating")
fun RatingBar.bindRating(rating: String) {
    val rate = rating.toFloat() / 2
    this.rating = rate
}