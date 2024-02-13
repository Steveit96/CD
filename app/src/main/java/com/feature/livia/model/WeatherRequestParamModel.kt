package com.feature.livia.model

data class WeatherRequestParamModel(
    val latitude: String,
    val longitude: String,
    val startDate: String,
    val endDate: String,
    val hourly: String,
)
