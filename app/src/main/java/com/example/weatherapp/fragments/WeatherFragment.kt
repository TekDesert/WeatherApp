package com.example.weatherapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.API.RetrofitInterface
import com.example.weatherapp.WeatherModel
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var currentWeather: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("I GO ONCREATE !!!!!!")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Get back our passed DATA
        val arguments = arguments
        val desired_string = arguments!!.getString("DATA") //Holds the city name that we clicked on


        var rf = Retrofit.Builder()
                .baseUrl(RetrofitInterface.BASE_URL) //Get our base url from our interface
                .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(RetrofitInterface::class.java)
        var call = API.weather( desired_string!! ,"921fc3aff1df3b13cd4e07ed0d553c2c") //First parameter is City, second parameter is API Key

        call?.enqueue(object:Callback<WeatherModel?>{
            override fun onResponse(
                    call: Call<WeatherModel?>,
                    response: Response<WeatherModel?>
            ) {
                if (response.code() == 200) {

                    //Congrats, the API call worked !

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


                }
            }

            override fun onFailure(call: Call<WeatherModel?>, t: Throwable) {
                println("Failed retrieving Data" + t)
            }

        })

        //api.openweathermap.org/data/2.5/weather?q=Paris&appid=921fc3aff1df3b13cd4e07ed0d553c2c

        println("hahahaha OLDDD = " + currentWeather + "\n\n\n\n")


        println("I GO ONVIEWCREATED !!!!!!")




        //weather_city.text = desired_string
        //weather_temp.text = desired_string

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}