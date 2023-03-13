package me.pm.marshall.ladd.mrshl.android

import android.app.Application

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
