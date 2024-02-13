package com.feature.livia.api

import com.feature.livia.model.WeatherModel
import com.feature.livia.model.WeatherRequestParamModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService,
) : ApiHelper {

    override suspend fun getWeatherForeCast(
        inputParam: WeatherRequestParamModel,
    ): Response<WeatherModel> {
        return apiService.getWeatherForeCast(
            latitude = inputParam.latitude,
            longitude = inputParam.longitude,
            startDate = inputParam.startDate,
            endDate = inputParam.endDate,
            hourly = inputParam.hourly,
        )
    }

}