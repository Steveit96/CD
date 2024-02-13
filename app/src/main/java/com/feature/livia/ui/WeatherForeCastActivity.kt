package com.feature.livia.ui

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feature.livia.utils.AppConstants.HOURLY_INPUT
import com.feature.livia.databinding.ActivityWeatherForeCastBinding
import com.feature.livia.model.WeatherRequestParamModel
import com.feature.livia.ui.adapter.WeatherForeCastAdapter
import com.feature.livia.utils.getRequiredDate
import com.feature.livia.utils.isOnline
import com.feature.livia.viewmodel.WeatherForeCastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForeCastActivity : AppCompatActivity(), LocationListener {

    private lateinit var binding: ActivityWeatherForeCastBinding

    private val viewModel: WeatherForeCastViewModel by viewModels()

    private lateinit var locationManager: LocationManager

    private val adapter = WeatherForeCastAdapter()

    private val locationPermissionCode = 2

    private var isRequestMade: Boolean = false

    private var isInternetAvailable: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherForeCastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isInternetAvailable = isOnline(this)
        getLocation()
        setupView()
    }

    private fun setupView() {
        binding.weatherList.let {
            it.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            it.adapter = adapter
        }
    }

    private fun observeWeatherData() {
        viewModel.weatherLiveData.observe(this) { state ->
            if (!state.isNullOrEmpty()) {
                hideLoader()
                adapter.submitList(state)
            } else {
                if (isInternetAvailable.not()) {
                    Toast.makeText(this, "Check your internet connection !", Toast.LENGTH_SHORT).show()
                } else {
                   Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun hideLoader() {
        binding.progress.visibility = View.GONE
        binding.weatherList.visibility = View.VISIBLE
    }


    override fun onLocationChanged(location: Location) {
        if (!isRequestMade) {
            Toast.makeText(this, "Location Detected", Toast.LENGTH_SHORT).show()
            viewModel.fetchWeatherForeCast(
                inputParam = WeatherRequestParamModel(
                    latitude = location.latitude.toString(),
                    longitude = location.longitude.toString(),
                    startDate = getRequiredDate().first,  // Current Date
                    endDate = getRequiredDate().second,  // Date after 7 days
                    hourly = HOURLY_INPUT,
                ),
                isInternetAvailable = isInternetAvailable,
            )
            observeWeatherData()
        }
        isRequestMade = true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode,
            )
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            0f,
            this,
        )
    }
}