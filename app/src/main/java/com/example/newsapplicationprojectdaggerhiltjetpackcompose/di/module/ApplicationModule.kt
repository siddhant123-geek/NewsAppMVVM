package com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module

import android.content.Context
import androidx.room.Room
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.api.NetworkService
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.MyAppDataBase
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.MyAppDataBaseService
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.BaseUrl
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.DatabaseName
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.DefaultNetworkHelper
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return DefaultNetworkHelper(context)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = "news-database"

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): MyAppDataBase {
        return Room.databaseBuilder(
            context,
            MyAppDataBase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: MyAppDataBase): MyAppDataBaseService {
        return MyAppDataBaseService(appDatabase)
    }
}