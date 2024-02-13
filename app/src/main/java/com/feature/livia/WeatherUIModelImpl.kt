package com.feature.livia

import android.annotation.SuppressLint
import com.feature.livia.model.WeatherModel
import com.feature.livia.model.WeatherUIModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

fun WeatherModel.convertToWeatherUIModel(): List<WeatherUIModel> {
    val groupedTemperatures = groupTemperaturesByDate()

    return groupedTemperatures.entries.mapIndexed { index, entry ->
        val date = entry.key.convertToDateFormat()
        val averageTemp = entry.value.average().roundToInt()
        WeatherUIModel(
            id = id,
            day = when (index) {
                0 -> "Today"
                1 -> "Tomorrow"
                else -> getDayOfWeek(entry.key)
            },
            date =  date,
            minTemperature = entry.value.minOrNull()?.roundToInt()?.toString() ?: "",
            maxTemperature = entry.value.maxOrNull()?.roundToInt()?.toString() ?: "",
            weatherImage = getWeatherSignImage(averageTemp)

        )
    }
}

@SuppressLint("NewApi")
fun WeatherModel.groupTemperaturesByDate(): Map<String, List<Float>> {
    val timeIntervals = hourlyForeCastModel.timeIntervals
    val temperatures = hourlyForeCastModel.temperatures

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.getDefault())

    return timeIntervals.zip(temperatures).groupBy(
        keySelector = { (first, _) ->
            LocalDateTime.parse(first, formatter).toLocalDate().toString()
        },
        valueTransform = { (_, second) -> second }
    )
}


fun String.convertToDateFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("EEEE, MMM dd", Locale.getDefault())

    return date?.let { outputFormat.format(it) } ?: ""
}

// Just Setting the image dummy with some rough logic
fun getWeatherSignImage(averageWeather: Int): Int {
    return when {
        averageWeather < 10 -> {
            R.drawable.rain_forecast
        }
        averageWeather > 10 -> {
            R.drawable.cloudy_forecast
        }
        else -> {
            0
        }
    }
}

fun getDayOfWeek(dateString: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = format.parse(dateString)
    val calendar = Calendar.getInstance()
    calendar.time = date ?: Date()

    val dayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.SUNDAY -> "Sunday"
        Calendar.MONDAY -> "Monday"
        Calendar.TUESDAY -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY -> "Thursday"
        Calendar.FRIDAY -> "Friday"
        Calendar.SATURDAY -> "Saturday"
        else -> ""
    }

    return dayOfWeek
}
