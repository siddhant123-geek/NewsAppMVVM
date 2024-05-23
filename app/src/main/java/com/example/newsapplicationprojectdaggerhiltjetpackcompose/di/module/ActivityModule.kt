package com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.ActivityContext
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.NewsSourcesRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.TopHeadlineRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.ViewModelProviderFactory
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country.CountriesPageAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbycountry.NewsByCountryViewModel
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource.NewsSourceAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource.NewsSourceViewModel
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline.TopHeadlineAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourcesViewModel(newsSourcesRepository: NewsSourcesRepository): NewsSourceViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourceViewModel::class) {
                NewsSourceViewModel(newsSourcesRepository)
            })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideNewsByCountryViewModel(topHeadlineRepository: TopHeadlineRepository): NewsByCountryViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsByCountryViewModel::class) {
                NewsByCountryViewModel(topHeadlineRepository)
            })[NewsByCountryViewModel::class.java]
    }

    @Provides
    fun provideNewsSourcesAdapter() = NewsSourceAdapter(ArrayList())

    @Provides
    fun provideCountriesPageAdapter() = CountriesPageAdapter(ArrayList())
}