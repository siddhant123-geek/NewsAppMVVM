package com.example.newsapplicationprojectdaggerhiltjetpackcompose

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ContentToSee

class MainActivityAdapter(private val context: Activity, private val array: ArrayList<ContentToSee>):
    ArrayAdapter<ContentToSee>(context, R.layout.list_item, array) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflator.inflate(R.layout.list_item, null)

        val contentText: TextView = view.findViewById(R.id.contentText)
        contentText.text = array[position].text
        return view
    }
}