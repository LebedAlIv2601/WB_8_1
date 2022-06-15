package com.example.wb_8_1

import android.app.Application
import android.content.Context
import com.example.wb_8_1.di.AppComponent
import com.example.wb_8_1.di.DaggerAppComponent
import com.example.wb_8_1.di.DataModule

class DotaApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(context = this)).build()
    }
}

val Context.appComponent: AppComponent
    get() = when(this){
        is DotaApp -> appComponent
        else -> applicationContext.appComponent
    }