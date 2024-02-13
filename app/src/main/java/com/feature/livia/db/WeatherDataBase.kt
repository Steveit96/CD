package com.feature.livia.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.feature.livia.model.WeatherModel

@Database(entities = [WeatherModel::class], version = 1)
abstract class WeatherDataBase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}