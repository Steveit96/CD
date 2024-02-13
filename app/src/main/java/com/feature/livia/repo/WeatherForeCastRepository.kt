package com.feature.livia.repo

import com.feature.livia.model.WeatherModel
import com.feature.livia.model.WeatherRequestParamModel

interface WeatherForeCastRepository {
    suspend operator fun invoke(
       inputParam: WeatherRequestParamModel,
       isInternetAvailable: Boolean,
    ): WeatherModel?
}