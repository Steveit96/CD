package com.feature.livia.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun getRequiredDate(): Pair<String, String> {
    val currentDate = Calendar.getInstance()

    val dateAfter7Days = currentDate.clone() as Calendar
    dateAfter7Days.add(Calendar.DAY_OF_MONTH, 6)

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formattedCurrentDate = dateFormat.format(currentDate.time)
    val formattedDateAfter7Days = dateFormat.format(dateAfter7Days.time)

    return Pair(formattedCurrentDate, formattedDateAfter7Days)
}