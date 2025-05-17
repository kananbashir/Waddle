package com.waddleup.auth.data.repository

import com.waddleup.auth.data.remote.AuthService
import com.waddleup.auth.domain.repository.AuthRepository
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.data.datastore.PreferenceDataStore
import com.waddleup.core.network.ConnectivityChecker
import com.waddleup.core.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

class AuthRepositoryImpl(
    dispatchersProvider: DispatchersProvider,
    connectivityChecker: ConnectivityChecker,
    retrofit: Retrofit,
    private val authService: AuthService,
    private val preferenceDataStore: PreferenceDataStore
): BaseRepository(dispatchersProvider, connectivityChecker, retrofit), AuthRepository {

    override suspend fun login() = safeApiCall {
        authService.login()
    }

    override suspend fun sendOtpToEmail() = safeApiCall {
        authService.sendOtpToEmail()
    }

    override suspend fun confirmOtp() = safeApiCall {
        authService.confirmOtp()
    }

    override suspend fun updatePassword() = safeApiCall {
        authService.updatePassword()
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return preferenceDataStore.getPreference(AuthPreferences.IS_LOGGED_IN, false)
    }

    override suspend fun setLoggedInStatus(isLoggedIn: Boolean) {
        preferenceDataStore.setPreference(AuthPreferences.IS_LOGGED_IN, isLoggedIn)
    }
}