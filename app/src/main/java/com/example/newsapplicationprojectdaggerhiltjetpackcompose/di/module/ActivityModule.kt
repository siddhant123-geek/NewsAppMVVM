package com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module

import dagger.hilt.android.components.ActivityComponent
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country.CountriesPageAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.instantsearch.InstantSearchAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.language.LanguagesAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource.NewsSourceAdapter
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline.TopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @ActivityScoped
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideInstantSearchAdapter() = InstantSearchAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideNewsSourcesAdapter() = NewsSourceAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideCountriesPageAdapter() = CountriesPageAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideLanguagesPageAdapter() = LanguagesAdapter(ArrayList())
}