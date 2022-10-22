package com.navermovie.presentation.view.detail.adapter

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.navermovie.presentation.R
import java.text.SimpleDateFormat

@BindingAdapter("bindDate")
fun TextView.bindDate(date: String) {
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
fun RatingBar.bindRating(rating: String) {
    val rate = rating.toFloat() / 2
    this.rating = rate
}

@BindingAdapter("bindOpenDate")
fun TextView.bindOpenDate(date: String) {
    val dateFormatter = SimpleDateFormat("yyyyMMdd")
    val date = dateFormatter.parse(date)
    text = SimpleDateFormat(context.getString(R.string.open_data_format)).format(date)
}
