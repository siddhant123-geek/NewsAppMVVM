package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.language

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.Language
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.databinding.ActivityLanguageActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LanguagesActivity : AppCompatActivity() {
    companion object {
        val languages = listOf(
            "Arabic",
            "German",
            "English",
            "Spanish",
            "French",
            "Hebrew",
            "Italian",
            "Dutch",
            "Norwegian",
            "Portuguese",
            "Russian",
            "Swedish",
            "Urdu",
            "Chinese"
        )
    }

    @Inject
    lateinit var adapter: LanguagesAdapter

    private lateinit var binding: ActivityLanguageActivityBinding

    private lateinit var languagesTexts: ArrayList<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        languagesTexts = ArrayList()

        for (i in languages.indices) {
            languagesTexts.add(Language(languages[i]))
        }
        val recyclerView = binding.languagesPageRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        adapter = LanguagesAdapter(languagesTexts)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}