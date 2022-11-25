package com.moviechacha.presentation.view.detail.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.moviechacha.entity.Directors
import com.moviechacha.presentation.R
import java.text.SimpleDateFormat

@BindingAdapter("bindDate")
fun TextView.bindDate(date: String) {
    text = if(date.isBlank()){
        context.getString(R.string.not_know)
    }else{
        date.subSequence(0, 4)
    }
}

@BindingAdapter("bindShowTime")
fun TextView.bindShowTime(time: String) {
    text = if (time.isBlank()) {
        ""
    } else {
        val hours = time.toInt() / 60
        val minutes = time.toInt() % 60
        "${hours}h ${minutes}m"
    }
}

@BindingAdapter("bindRating")
fun TextView.bindRating(rating: String) {
    text = if (rating.isBlank()) {
        "0.0"
    } else {
        rating
    }
}

@BindingAdapter("bindOpenDate")
fun TextView.bindOpenDate(date: String) {
    text = if (date.isBlank()) {
        ""
    } else {
        val dateFormatter = SimpleDateFormat("yyyyMMdd")
        val date = dateFormatter.parse(date)
        SimpleDateFormat(context.getString(R.string.open_data_format)).format(date)
    }
}

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let {
        if (imageUrl.isNotBlank()) {
            Glide.with(this).load(imageUrl).into(this)
        }
    }
}

@BindingAdapter("bindDirector")
fun TextView.bindDirector(directorList: List<Directors>?) {
    var tmpDirectorText = ""
    directorList?.let { list ->
        for (director in list) {
            tmpDirectorText += "(${director.name}) "
        }
    }
    text = tmpDirectorText
}

@BindingAdapter("bindHtml")
fun TextView.bindHtml(html: String?) {
    html?.let {
        text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

@BindingAdapter("bindBookmark")
fun ImageView.bindBookmark(state: Boolean){
    if (state){
        setImageResource(R.drawable.ic_book_mark_on)
    }else{
        setImageResource(R.drawable.ic_book_mark_off)
    }
}