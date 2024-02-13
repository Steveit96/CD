package com.feature.livia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.livia.convertToWeatherUIModel
import com.feature.livia.model.WeatherRequestParamModel
import com.feature.livia.model.WeatherUIModel
import com.feature.livia.repo.WeatherForeCastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherForeCastViewModel @Inject constructor(
    private val weatherForeCastRepository: WeatherForeCastRepository,
) : ViewModel() {

    private val _weatherLiveData = MutableLiveData<List<WeatherUIModel>?>()

    val weatherLiveData: LiveData<List<WeatherUIModel>?>
        get() = _weatherLiveData

    fun fetchWeatherForeCast(
        inputParam: WeatherRequestParamModel,
        isInternetAvailable: Boolean,
    ) {
        viewModelScope.launch {
            _weatherLiveData.postValue(
                weatherForeCastRepository(
                    inputParam,
                    isInternetAvailable,
                )?.convertToWeatherUIModel()
            )
        }
    }

}