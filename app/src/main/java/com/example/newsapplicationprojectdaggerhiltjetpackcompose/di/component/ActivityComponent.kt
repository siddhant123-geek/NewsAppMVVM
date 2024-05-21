package com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.component

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.ActivityScope
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module.ActivityModule
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}