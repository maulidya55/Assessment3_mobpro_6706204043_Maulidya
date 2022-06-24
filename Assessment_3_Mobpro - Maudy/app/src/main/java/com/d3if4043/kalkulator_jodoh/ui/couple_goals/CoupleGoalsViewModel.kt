package com.d3if4043.kalkulator_jodoh.ui.couple_goals

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if4043.kalkulator_jodoh.MainActivity
import com.d3if4043.kalkulator_jodoh.network.UpdateWorker
import java.util.concurrent.TimeUnit

class CoupleGoalsViewModel: ViewModel() {
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}