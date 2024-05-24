package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.language

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.Language
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.databinding.ListItemBinding
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbylanguage.NewsByLanguageActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.ISO_CODE_KEY
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.IsoCodes

class LanguagesAdapter(private val languagesList: ArrayList<Language>):
    RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            binding.contentText.text = language.name
            itemView.setOnClickListener {
                val clickedItemText = language.name
                val isoCode = IsoCodes.languageToIsoCodeMap[clickedItemText]
                val intent = Intent(it.context, NewsByLanguageActivity::class.java)
                intent.putExtra(ISO_CODE_KEY, isoCode)
                ContextCompat.startActivity(it.context, intent, Bundle.EMPTY)
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
        return languagesList.size;
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(languagesList[position])
    }

    fun addData(list: List<Language>) {
        languagesList.addAll(list)
    }
}