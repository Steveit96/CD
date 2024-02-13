package com.feature.livia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.feature.livia.utils.AppConstants
import com.google.gson.annotations.SerializedName
import com.feature.livia.db.RoomConverters

@Entity(tableName = AppConstants.WEATHER_TABLE)
@TypeConverters(RoomConverters::class)
data class WeatherModel(

    @PrimaryKey
    val id: String,

    @SerializedName("latitude")
    val latitude: Float,

    @SerializedName("longitude")
    val longitude: Float,

    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,

    @SerializedName("timezone")
    val timeZone: String,

    @SerializedName("elevation")
    val elevation: Double,

    @SerializedName("hourly")
    val hourlyForeCastModel: HourlyForeCastModel,
)