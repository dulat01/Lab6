package com.example.lab6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class CustomListAdapter(private val context: Context, private val listData: List<Country>) :
    BaseAdapter() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(position: Int): Any {
        return listData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null)
            holder = ViewHolder()
            holder.flagView = convertView.findViewById<View>(R.id.imageView_flag) as ImageView
            holder.countryNameView =
                convertView.findViewById<View>(R.id.textView_countryName) as TextView
            holder.populationView =
                convertView.findViewById<View>(R.id.textView_population) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val country = listData[position]
        holder.countryNameView!!.text = country.countryName
        holder.populationView!!.text = "Population: " + country.population

        val imageId = this.getMipmapResIdByName(country.flagName)
        holder.flagView!!.setImageResource(imageId)

        return convertView
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap)
    fun getMipmapResIdByName(resName: String?): Int {
        val pkgName = context.packageName
        val resID = context.resources.getIdentifier(resName, "mipmap", pkgName)
        return resID
    }

    internal class ViewHolder {
        var flagView: ImageView? = null
        var countryNameView: TextView? = null
        var populationView: TextView? = null
    }
}
