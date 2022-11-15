package com.navermovie.presentation.view.search.adapter

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter("bindLoading")
fun LottieAnimationView.bindLoading(state: Boolean) {
    isVisible = state
}