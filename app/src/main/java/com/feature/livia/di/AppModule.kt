package com.feature.livia.di

import com.feature.livia.utils.AppConstants
import com.feature.livia.api.ApiHelper
import com.feature.livia.api.ApiHelperImpl
import com.feature.livia.api.ApiService
import com.feature.livia.repo.WeatherForeCastRepository
import com.feature.livia.repo.WeatherForeCastRepositoryImpl
import com.feature.livia.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideBaseUrl() = AppConstants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):
            Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(
        apiHelper: ApiHelperImpl,
    ): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideWeatherForeCastRepo(
        weatherForeCaseRepositoryImpl: WeatherForeCastRepositoryImpl,
    ): WeatherForeCastRepository = weatherForeCaseRepositoryImpl



}