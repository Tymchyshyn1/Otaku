package com.example.rxjava.app

import android.app.Application
import com.example.a16_rxjava_data.di.DataModule
import com.example.rxjava.R
import com.example.rxjava.app.di.AppComponent
import com.example.rxjava.app.di.DaggerAppComponent
import koleton.SkeletonLoader
import koleton.SkeletonLoaderFactory

class App : Application(), SkeletonLoaderFactory {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .dataModule(DataModule(context = this))
            .build()
    }

    override fun newSkeletonLoader(): SkeletonLoader {
        return SkeletonLoader.Builder(this)
            .color(R.color.skeleton_color)
            .cornerRadius(15F)
            .build()
    }
}