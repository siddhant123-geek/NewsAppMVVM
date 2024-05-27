package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.TopHeadlineRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants
import javax.inject.Inject

class MyNewsFetchWork @Inject constructor(context: Context,
                                          workParams: WorkerParameters,
                                          private val repo: TopHeadlineRepository
) : Worker(context, workParams) {

    companion object {
        const val WORK_MANAGER_TAG = "MyNewsFetchWork"
    }

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return try {
            repo.getTopHeadlines(AppConstants.COUNTRY)
            Result.Success()
        }
        catch (e: Error) {
            Result.failure()
        }
    }
}