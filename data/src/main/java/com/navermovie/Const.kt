package com.navermovie

import java.text.SimpleDateFormat
import java.util.*

const val KOFIC_KEY = "7757453def15a4634a7ae0ff96f6c92c"

const val NAVER_ID_KEY = "99VXvJCtfXDqG8qIT6mo"

const val NAVER_SECRET_KEY = "kBpFgVkDrP"

const val YOUTUBE_KEY = "AIzaSyA9NVWdrIuRxh4NFvZM3TIyVmbv8RI1PpM"

const val KAKAO_KEY = "0aa27526a3b1f6056b36ea08b23446c3"

const val KMDB_KEY = "YD696W32L73AA522H8UC"

const val PLOT_ERROR = "줄거리를 받아올 수 없습니다"

const val PAGE_SIZE = 30

fun getCurrentDate(): String {
    return SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - 1000L * 60L * 60L * 24L)
        .toString()
}

fun getLastWeek(): String {
    val df = SimpleDateFormat("yyyyMMdd")
    val cal = Calendar.getInstance()
    cal.time = Date()
    cal.add(Calendar.DATE, -7)
    return df.format(cal.time)
}