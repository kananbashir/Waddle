package com.waddleup.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

object DataStoreFactory {

    fun create(
        context: Context,
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        storeName: String,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(scope = scope) {
            context.preferencesDataStoreFile(storeName)
        }
    }

}