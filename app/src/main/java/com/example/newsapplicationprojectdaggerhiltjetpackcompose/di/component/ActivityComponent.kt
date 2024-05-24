package com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.component

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.ActivityScope
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module.ActivityModule
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country.CountriesPageActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.language.LanguagesActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbycountry.NewsByCountryActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newsbylanguage.NewsByLanguageActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource.NewsSourceActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)
    fun inject(activity: NewsSourceActivity)

    fun inject(activity: CountriesPageActivity)

    fun inject(activity: NewsByCountryActivity)

    fun inject(activity: LanguagesActivity)

    fun inject(activity: NewsByLanguageActivity)
}