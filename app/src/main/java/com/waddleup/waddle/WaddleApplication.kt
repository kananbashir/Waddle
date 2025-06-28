package com.waddleup.waddle

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.waddleup.waddle.di.KoinModuleLoader
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

class WaddleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@WaddleApplication)
            modules(KoinModuleLoader.loadModules())
        }

        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 0
                }
            )
            setDefaultsAsync(
                mapOf(
                    "categories" to """["Restaurant","Hotel","Clothes"]"""
                )
            )
            fetchAndActivate()
        }
    }
}