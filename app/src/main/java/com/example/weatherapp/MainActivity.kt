package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import com.example.weatherapp.API.RetrofitInterface
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()




        var rf = Retrofit.Builder()
                .baseUrl(RetrofitInterface.BASE_URL) //Get our base url from our interface
                .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(RetrofitInterface::class.java)
        var call = API.weather( "casablanca" ,"921fc3aff1df3b13cd4e07ed0d553c2c") //First parameter is City, second parameter is API Key

        call?.enqueue(object: Callback<WeatherModel?> {
            override fun onResponse(
                    call: Call<WeatherModel?>,
                    response: Response<WeatherModel?>
            ) {
                if (response.code() == 200) {

                    println("Response done !")

                    val intent = Intent(this@MainActivity, HomeActivity::class.java) //Chose start up activity here
                    startActivity(intent)
                    finish()

                    //Congrats, the API call worked !
                    /*

                    val weatherResponse = response.body()!!

                    val Temperature = weatherResponse.main!!.temp - 273.15



                    weather_temp!!.text = String.format("%.0f", Temperature) + "°" //Rounding the temperature to the nearest degree
                    weather_city!!.text = weatherResponse.name

                    currentWeather = weatherResponse.weather[0]!!.main //private attribute variable weather main that we will pass to on viewdidCreate() to change wallpaper

                    //Select our wallpaper to modify according to the weather
                    val wallpaper = Weather_container
                    //Get our weather :
                    val weatherAtSelectedCity = currentWeather


                    //$StringGenerated

                    if (wallpaper != null){ //Verify that our wallpaper actually exist or else we get an error

                        if(weatherAtSelectedCity == "Rain"){

                            //Select and assign our new weather image
                            //val id = resources.getIdentifier("com.example.weatherapp:drawable/rain", null, null)
                            //wallpaper.setImageResource(id)

                            weather_icon.setBackgroundResource(R.drawable.rain)

                        }else if (weatherAtSelectedCity == "Clouds"){

                            //Select and assign our new weather image
                            val id = resources.getIdentifier("com.example.weatherapp:drawable/night", null, null)
                            wallpaper.setImageResource(id)

                            weather_icon.setBackgroundResource(R.drawable.cloud)

                        }else if (weatherAtSelectedCity == "Clear"){

                            //Select and assign our new weather image
                            val id = resources.getIdentifier("com.example.weatherapp:drawable/sunset", null, null)
                            wallpaper.setImageResource(id)

                            weather_icon.setBackgroundResource(R.drawable.sun)

                        }

                    }else{
                        weather_city.text = "Error !" //Display error
                    }

                    /*
                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "NAME: " +
                            weatherResponse.name +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    println(stringBuilder)*/

                    */
                }
            }

            override fun onFailure(call: Call<WeatherModel?>, t: Throwable) {
                println("Failed retrieving Data" + t)
            }

        })



        /*
       Handler().postDelayed({
           val intent = Intent(this@MainActivity, HomeActivity::class.java) //Chose start up activity here
           startActivity(intent)
           finish()
       }, 2000)*/
    }
}