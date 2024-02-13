package com.feature.livia.api

import com.feature.livia.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getWeatherForeCast(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("hourly") hourly: String,
    ): Response<WeatherModel>
}