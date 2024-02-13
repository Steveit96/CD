package com.feature.livia.di

import android.app.Application
import androidx.room.Room
import com.feature.livia.utils.AppConstants
import com.feature.livia.db.WeatherDao
import com.feature.livia.db.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WeatherDbModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, WeatherDataBase::class.java, AppConstants.WEATHER_DB)
            .allowMainThreadQueries()
            .addMigrations()
            .build()


    @Provides
    @Singleton
    internal fun provideFeedDao(db: WeatherDataBase): WeatherDao {
        return db.weatherDao()
    }
}