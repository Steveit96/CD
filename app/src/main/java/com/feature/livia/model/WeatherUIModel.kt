package com.feature.livia.model

import androidx.annotation.DrawableRes


data class WeatherUIModel(
    val id: String,
    val date: String,
    val day: String,
    val maxTemperature: String,
    val minTemperature: String,
    @DrawableRes val weatherImage: Int,
)