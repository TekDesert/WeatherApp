package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weatherapp.fragments.CitiesFragment
import com.example.weatherapp.fragments.HomeFragment
import com.example.weatherapp.fragments.MapsFragment
import com.example.weatherapp.fragments.WeatherFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

//This extension was added manually to allow to select menu by id directly without the "findViewById...."
import kotlinx.android.synthetic.main.activity_navigation.*



class HomeActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        supportActionBar?.hide()

        val HomeFragment = HomeFragment()
        val CitiesFragment = CitiesFragment()
        val MapsFragment = MapsFragment()
        val WeatherModel = WeatherFragment()

        makeCurrentFragment(HomeFragment)

        //We can select directly bottom_navigation with it's id and without "FindViewById..." thanks to the import mentioned above
        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.ic_home -> makeCurrentFragment(HomeFragment)
                R.id.ic_weather -> makeCurrentFragment(CitiesFragment)
                R.id.ic_location -> makeCurrentFragment(MapsFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment) //Replace our layout with our new fragment
        commit()
    }


}