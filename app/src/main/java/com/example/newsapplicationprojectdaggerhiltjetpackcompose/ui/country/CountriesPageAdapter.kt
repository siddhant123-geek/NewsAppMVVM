package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.Country
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.databinding.ListItemBinding
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbycountry.NewsByCountryActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.ISO_CODE_KEY
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.IsoCodes

class CountriesPageAdapter(private val countriesList: ArrayList<Country>):
    RecyclerView.Adapter<CountriesPageAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.contentText.text = country.name
            itemView.setOnClickListener {
                val clickedItemText = country.name
                val isoCode = IsoCodes.countryToISOCodeMap[clickedItemText]
                val intent = Intent(it.context, NewsByCountryActivity::class.java)
                intent.putExtra(ISO_CODE_KEY, isoCode)
                startActivity(it.context, intent, Bundle.EMPTY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return countriesList.size;
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    fun addData(list: List<Country>) {
        countriesList.addAll(list)
    }
}