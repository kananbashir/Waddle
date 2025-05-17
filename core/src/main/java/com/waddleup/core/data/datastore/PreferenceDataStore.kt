package com.waddleup.core.data.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow


/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

interface PreferenceDataStore {
    fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> setPreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    fun getData(): Flow<Preferences>
}