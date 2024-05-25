package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.Country
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.databinding.ActivityCountriesPageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountriesPageActivity: AppCompatActivity() {
    companion object {
        val countries = listOf(
            "United Arab Emirates",
            "Argentina",
            "Austria",
            "Australia",
            "Belgium",
            "Bulgaria",
            "Brazil",
            "United States",
            "Canada",
            "Switzerland",
            "China",
            "Colombia",
            "Cuba",
            "Germany",
            "Egypt"
        )
    }

    @Inject
    lateinit var adapter: CountriesPageAdapter

    private lateinit var binding: ActivityCountriesPageBinding

    private lateinit var countriesTexts: ArrayList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countriesTexts = ArrayList()

        for(i in countries.indices) {
            countriesTexts.add(Country(countries[i]))
        }
        val recyclerView = binding.countriesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        adapter = CountriesPageAdapter(countriesTexts)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}