package com.example.newsapplicationprojectdaggerhiltjetpackcompose

import android.app.Application
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.MyNewsFetchWork
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Enque the task to be performed by work manager
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Create a retry policy with exponential backoff
        val retryPolicy = BackoffPolicy.EXPONENTIAL
        val retryInterval = 15L // Retry every 15 minutes
        val retryIntervalTimeUnit = TimeUnit.MINUTES

        val fetchNewsRequest = PeriodicWorkRequestBuilder<MyNewsFetchWork>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        )
            .setConstraints(constraints)
            .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
            .setBackoffCriteria(retryPolicy, retryInterval, retryIntervalTimeUnit)
            .addTag(MyNewsFetchWork.WORK_MANAGER_TAG)
            .build()
        WorkManager
            .getInstance()
            .enqueueUniquePeriodicWork(
                MyNewsFetchWork.WORK_MANAGER_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                fetchNewsRequest
            )
    }
}

private fun calculateInitialDelay(): Long {
    val currentTimeMillis = System.currentTimeMillis()
    // getting the current time in MS
    val currentTime = currentTimeMillis % (24 * 60 * 60 * 1000)
    val desiredTime = 5 * 60 * 60 * 1000
    val initialDelay = desiredTime - currentTime
    // if current time is ahead of 5 A.M, then wait for the 5 A.M of the next day
    // else wait for the initialDelay time
    return if (initialDelay < 0) {
        initialDelay + 24 * 60 * 60 * 1000
    } else {
        initialDelay
    }
}