package com.example.weatherapp.API

import com.example.weatherapp.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("weather?")
    fun weather(@Query("q") q: String, @Query("APPID") app_id: String) : Call<WeatherModel?>?

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        //api.openweathermap.org/data/2.5/weather
    }


}