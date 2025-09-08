package com.example.baseandroidproject

import android.app.Application
import com.example.utilities.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseAndroidProjectApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(message = "Application is launched")
    }

    companion object {
        const val TAG = "OversteerF1Application"
    }
}