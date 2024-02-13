package com.feature.livia.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.feature.livia.model.WeatherUIModel

class WeatherForeCastDiffCallBack : DiffUtil.ItemCallback<WeatherUIModel>() {

    override fun areItemsTheSame(oldItem: WeatherUIModel, newItem: WeatherUIModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WeatherUIModel, newItem: WeatherUIModel) =
        oldItem == newItem
}
