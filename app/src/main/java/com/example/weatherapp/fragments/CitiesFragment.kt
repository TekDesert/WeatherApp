package com.example.weatherapp.fragments

import android.R.attr.fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.fragment_cities.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CitiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CitiesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }

        //supportActionBar?.hide()*/

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_cities, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //THIS FUNCTION WAS ADDED, OTHERWISE WE CANNOT GET OUT LISTVIEW IF THE FRAGMENT IS NOT YET CREATED !!!!
        super.onViewCreated(view, savedInstanceState)

        val listView = home_listview //Get Home List View activity - Must be in "onViewCreated" otherwise we will get null and app will crash

        val context = context as Context;

        listView.adapter = MyCustomAdapter(context)


        //Add on listView click listener, when an element is clicked, we will get the city name and display the new page
        listView.setOnItemClickListener{ parent, view, position, id ->
            val element = listView.adapter.getItem(position) // The item that was clicked
            println(element)



            //Define our new fragment destination
            val destinationFragment = WeatherFragment()

            //Prepare data to be passed
            val arguments = Bundle()
            arguments.putString("DATA", element.toString())
            destinationFragment.setArguments(arguments)

            /*val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
            ft.replace(R.id.fl_wrapper, destinationFragment, "NewFragmentTag")
            ft.commit()*/

            //call change fragment function
            makeCurrentFragment(destinationFragment)

        }

    }

    //call change fragment function
    private fun makeCurrentFragment(fragment: Fragment) {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.fl_wrapper, fragment, "NewFragmentTag")
        ft.commit()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CitiesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CitiesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    //THIS ADAPTER IS RESPONSIBLE FOR THE DISPLAY OF OUR LISTVIEW
    private class MyCustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        private val names = arrayListOf<String>("Paris,fr", "Rome,it", "Berlin,de", "Moscow,ru", "London,uk", "Miami,us", "Montreal,ca")

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
            return names[position]
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

            if (names.get(position) == "Paris,fr"){
                cityPicture.setImageResource(R.drawable.newparis);
            }else if (names.get(position) == "Berlin,de"){
                cityPicture.setImageResource(R.drawable.newberlin);
            }else if (names.get(position) == "Rome,it"){
                cityPicture.setImageResource(R.drawable.newrome);
            }else if (names.get(position) == "Moscow,ru"){
                cityPicture.setImageResource(R.drawable.newmoscow);
            }else if (names.get(position) == "London,uk"){
                cityPicture.setImageResource(R.drawable.newlondon);
            }else if (names.get(position) == "Miami,us"){
                cityPicture.setImageResource(R.drawable.miami);
            }else{
                cityPicture.setImageResource(R.drawable.montreal);
            }




            return row_main

            // val textView = TextView(mContext)
            //textView.text = "Here is my ROW for my ListView"
            //return textView

        }

    }


}