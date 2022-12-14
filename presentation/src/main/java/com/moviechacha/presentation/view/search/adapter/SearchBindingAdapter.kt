package com.moviechacha.presentation.view.search.adapter

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter("bindLoading")
fun LottieAnimationView.bindLoading(state: Boolean) {
    isVisible = state
}