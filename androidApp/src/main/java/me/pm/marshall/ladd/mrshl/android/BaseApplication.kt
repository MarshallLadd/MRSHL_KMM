package me.pm.marshall.ladd.mrshl.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        lateinit var INSTANCE: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}
