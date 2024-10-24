package com.pwfb.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getCurrentToday(): String {
    return SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    ).format(Calendar.getInstance(Locale.getDefault()).time)
}

fun getCurrentTime(): String {
    return SimpleDateFormat(
        "HH:mm:ss",
        Locale.getDefault()
    ).format(Calendar.getInstance(Locale.getDefault()).time)
}