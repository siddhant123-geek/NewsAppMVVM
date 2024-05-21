package com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.component

import android.content.Context
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.MyNewsApplication
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.api.NetworkService
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.ApplicationContext
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module.ApplicationModule
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.TopHeadlineRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyNewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

}