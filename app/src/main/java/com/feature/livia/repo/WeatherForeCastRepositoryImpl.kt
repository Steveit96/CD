package com.feature.livia.repo

import com.feature.livia.api.ApiHelper
import com.feature.livia.db.WeatherDataBase
import com.feature.livia.model.WeatherModel
import com.feature.livia.model.WeatherRequestParamModel
import java.util.UUID
import javax.inject.Inject

class WeatherForeCastRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    dataBase: WeatherDataBase,
): WeatherForeCastRepository {
    private val weatherDb = dataBase.weatherDao()

    override suspend fun invoke(
        inputParam: WeatherRequestParamModel,
        isInternetAvailable: Boolean,
    ): WeatherModel? {
        return try {
            if (isInternetAvailable) {
                val result = apiHelper.getWeatherForeCast(
                    inputParam,
                )
                if (result.isSuccessful) {
                    weatherDb.clearData()
                    result.body()?.let {
                        weatherDb.insert(
                            weather = it.copy(id = UUID.randomUUID().toString()),
                        )
                    }
                }
            }
            weatherDb.fetchAllData()
        } catch (ex: Exception) {
            weatherDb.fetchAllData()
        }
    }

    companion object {
        const val TAG = "WeatherForeCastRepositoryImpl"
    }
}