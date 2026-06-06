package com.example.bookshop

import android.app.Application
import timber.log.Timber

class BookShopApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}