package com.waddleup.auth.data.repository

import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

object AuthPreferences {
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
}