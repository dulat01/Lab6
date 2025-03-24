package com.example.lab6

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image_details = listData
        val listView = findViewById<View>(R.id.listView) as ListView
        listView.adapter = CustomListAdapter(this, image_details)

        // When the user clicks on the ListItem
        listView.onItemClickListener =
            OnItemClickListener { a, v, position, id ->
                val o = listView.getItemAtPosition(position)
                val country = o as Country
                Toast.makeText(
                    this@MainActivity,
                    "Selected: " + country.countryName,
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private val listData: List<Country>
        get() {
            val list: MutableList<Country> = ArrayList()
            val vietnam = Country("Vietnam", "vn", 98000000)
            val usa = Country("United States", "us", 320000000)
            val russia = Country("Russia", "ru", 142000000)

            list.add(vietnam)
            list.add(usa)
            list.add(russia)

            return list
        }
}
