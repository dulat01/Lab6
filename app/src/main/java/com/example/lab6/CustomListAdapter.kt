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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_layout, parent, false)
            holder = ViewHolder()
            holder.flagView = view.findViewById(R.id.imageView_flag)
            holder.countryNameView = view.findViewById(R.id.textView_countryName)
            holder.populationView = view.findViewById(R.id.textView_population)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val country = listData[position]
        holder.countryNameView?.text = country.countryName
        holder.populationView?.text = "Population: ${country.population}"

        val imageId = getMipmapResIdByName(country.flagName)
        if (imageId != 0) {
            holder.flagView?.setImageResource(imageId)
        }

        return view!!
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap)
    private fun getMipmapResIdByName(resName: String?): Int {
        val pkgName = context.packageName
        // Добавляем проверку на null
        return if (resName != null) {
            context.resources.getIdentifier(resName, "mipmap", pkgName)
        } else {
            0
        }
    }

    internal class ViewHolder {
        var flagView: ImageView? = null
        var countryNameView: TextView? = null
        var populationView: TextView? = null
    }
}
