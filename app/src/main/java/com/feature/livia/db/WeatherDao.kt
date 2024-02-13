package com.feature.livia.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.feature.livia.model.WeatherModel
import com.feature.livia.utils.AppConstants


@Dao
interface WeatherDao {

    @Insert
    fun insert(weather: WeatherModel)

    @Query("SELECT * FROM ${AppConstants.WEATHER_TABLE}")
    fun fetchAllData(): WeatherModel?

    @Query("DELETE FROM ${AppConstants.WEATHER_TABLE}")
    fun clearData()
}