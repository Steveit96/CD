package com.feature.livia.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.feature.livia.model.WeatherUIModel


class WeatherForeCastAdapter :
    ListAdapter<WeatherUIModel, WeatherForeCastViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForeCastViewHolder {
        return WeatherForeCastViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: WeatherForeCastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = WeatherForeCastDiffCallBack()
    }
}
