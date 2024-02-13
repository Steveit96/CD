package com.feature.livia.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import com.feature.livia.R
import com.feature.livia.databinding.LayoutItemWeatherBinding
import com.feature.livia.model.WeatherUIModel


class WeatherForeCastViewHolder(
    parent: ViewGroup,
) : BaseViewHolder(parent, R.layout.layout_item_weather) {

    @VisibleForTesting
    val binding = LayoutItemWeatherBinding.bind(itemView)

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(item: WeatherUIModel) {
        with(binding) {
            dayText.text = item.day
            dateTxt.text = item.date
            // Not suggestible way. Doing this for timbering
            val textMinAndMaxWeather = item.minTemperature+"°"+" / "+item.maxTemperature+"°"
            txtTemp.text = textMinAndMaxWeather

            imgWeather.background = if (item.weatherImage != 0) {
                ContextCompat.getDrawable(context, item.weatherImage)
            } else {
                ContextCompat.getDrawable(context, R.drawable.default_weather)
            }
        }
    }
}
