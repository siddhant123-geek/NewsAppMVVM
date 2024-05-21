package com.example.newsapplicationprojectdaggerhiltjetpackcompose

import android.app.Application
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.component.ApplicationComponent
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.component.DaggerApplicationComponent
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module.ApplicationModule

class MyNewsApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}