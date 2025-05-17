package com.waddleup.core.di

import com.waddleup.core.base.util.DefaultDispatchersProvider
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.data.datastore.DataStoreFactory
import com.waddleup.core.data.datastore.PreferenceDataStore
import com.waddleup.core.data.datastore.PreferenceDataStoreImpl
import com.waddleup.core.di.util.addModules
import com.waddleup.core.network.ConnectivityChecker
import com.waddleup.core.network.ConnectivityCheckerImpl
import org.koin.android.ext.koin.androidContext

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

val dataModule = addModules(
    dispatchers = {
        single<DispatchersProvider> { DefaultDispatchersProvider() }
    },
    dataStorage = {
        single {
            DataStoreFactory.create(
                context = androidContext(),
                storeName = "app_preferences"
            )
        }

        single<PreferenceDataStore> {
            PreferenceDataStoreImpl(get())
        }
    },
    other = {
        single<ConnectivityChecker> { ConnectivityCheckerImpl(get()) }
    }
)