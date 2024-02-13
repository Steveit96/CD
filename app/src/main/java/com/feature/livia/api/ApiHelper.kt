package com.feature.livia.api

import com.feature.livia.model.WeatherModel
import com.feature.livia.model.WeatherRequestParamModel
import retrofit2.Response
import retrofit2.http.Query

interface ApiHelper {
   suspend fun getWeatherForeCast(
      inputParam: WeatherRequestParamModel,
   ): Response<WeatherModel>
}