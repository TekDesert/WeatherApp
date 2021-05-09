package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CountryChoiceActivity : AppCompatActivity() {

    /*
    var listView: ListView;
    val mTitle = arrayOf<String>( "Facebook", "Twitter", "Instagram")
    val mDescription = arrayOf<String>( "Facebook Description", "Twitter Description", "Instagram Description")

    val images =  arrayOf<Int>(R.drawable.berlin, R.drawable.berlin, R.drawable.london, R.drawable.moscow, R.drawable.rome);
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countrychoice)

        /*
        val listView = findViewById<ListView>(R.id.home_listview) //Get Home List View activity
        //val rowColor = Color.parseColor("#0079ca")
        //listView.setBackgroundColor(rowColor)

        listView.adapter = MyCustomAdapter(this) //This needs to be my custom adapter telling my list what to render

        //supportActionBar?.hide()
        */
    }

    /*

    //THIS ADAPTER IS RESPONSIBLE FOR THE DISPLAY OF OUR LISTVIEW
    private class MyCustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        private val names = arrayListOf<String>("paris", "rome", "berlin", "moscow", "london")

        init{
            mContext = context //Gets the context passed in the paramater
        }

        // responsible for how many rows in our list
        override fun getCount(): Int {
            return names.size; // Number of rows depend on array length
        }

        //Get item id
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //Ignore for now
        override fun getItem(position: Int): Any {
           return "TEST STRING"
        }

        //Responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutInflater = LayoutInflater.from(mContext)

            val row_main = layoutInflater.inflate(R.layout.row_home, viewGroup, false) //Get our custom row view called "row_home.xml"

            val cityDegree = row_main.findViewById<TextView>(R.id.city_degree)
            cityDegree.text = "2$position °C" //give our custom row View our title

            val cityName = row_main.findViewById<TextView>(R.id.city_name)
            cityName.text = names.get(position)



            val cityPicture = row_main.findViewById<TextView>(R.id.city_picture) as ImageView

            Log.d("NAME", names.get(position));

            if (names.get(position) == "paris"){
                cityPicture.setImageResource(R.drawable.paris);
            }else if (names.get(position) == "berlin"){
                cityPicture.setImageResource(R.drawable.berlin);
            }else if (names.get(position) == "rome"){
                cityPicture.setImageResource(R.drawable.rome);
            }else if (names.get(position) == "moscow"){
                cityPicture.setImageResource(R.drawable.moscow);
            }else{
                cityPicture.setImageResource(R.drawable.london);
            }




            return row_main

           // val textView = TextView(mContext)
            //textView.text = "Here is my ROW for my ListView"
            //return textView

        }

    } */


}