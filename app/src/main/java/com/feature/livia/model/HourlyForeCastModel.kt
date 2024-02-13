package com.feature.livia.model

import com.google.gson.annotations.SerializedName

data class HourlyForeCastModel(
    @SerializedName("time")
    val timeIntervals: List<String>,

    @SerializedName("temperature_2m")
    val temperatures: List<Float>,

    @SerializedName("rain")
    val rain: List<Float>
)
