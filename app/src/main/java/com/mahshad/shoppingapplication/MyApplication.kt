package com.mahshad.shoppingapplication

import android.app.Application
import com.mahshad.shoppingapplication.di.AppComponent
import com.mahshad.shoppingapplication.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}

