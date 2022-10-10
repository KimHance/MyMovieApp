package com.navermovie

import java.text.SimpleDateFormat

const val KOFIC_KEY = "7757453def15a4634a7ae0ff96f6c92c"

const val NAVER_ID_KEY = "99VXvJCtfXDqG8qIT6mo"

const val NAVER_SECRET_KEY = "kBpFgVkDrP"

fun getCurrentDate(): String {
    return SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()).toString()
}