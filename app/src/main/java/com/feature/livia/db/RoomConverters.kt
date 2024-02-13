package com.feature.livia.db

import androidx.room.TypeConverter
import com.feature.livia.model.HourlyForeCastModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class RoomConverters {

    private val gson: Gson = GsonBuilder().create()

    @TypeConverter
    fun fromStringToHourlyForeCastModel(value: String): HourlyForeCastModel {
        val listType = object : TypeToken<HourlyForeCastModel>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromHourlyForeCastModelToGson(value: HourlyForeCastModel): String =
        gson.toJson(value)

}