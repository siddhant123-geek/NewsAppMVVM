package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.instantsearch

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiArticle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.databinding.TopHeadlineItemLayoutBinding

class InstantSearchAdapter (
    private val articleList: ArrayList<ApiArticle>
) : RecyclerView.Adapter<InstantSearchAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ApiArticle) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<ApiArticle>) {
        // Here list.clear is important for making the last list empty and
        // add the new data corresponding
        // to the new keyword entered
        articleList.clear()
        articleList.addAll(list)
    }

}