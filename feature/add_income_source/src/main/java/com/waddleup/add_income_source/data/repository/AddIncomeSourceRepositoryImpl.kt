package com.waddleup.add_income_source.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.waddleup.add_income_source.domain.repository.AddIncomeSourceRepository
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.network.ConnectivityChecker
import com.waddleup.core.repository.BaseRepository
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

/**
 * Created on 6/28/2025
 * @author Kanan Bashir
 */

class AddIncomeSourceRepositoryImpl(
    dispatchersProvider: DispatchersProvider,
    connectivityChecker: ConnectivityChecker,
    retrofit: Retrofit,
    private val remoteConfig: FirebaseRemoteConfig
): BaseRepository(dispatchersProvider, connectivityChecker, retrofit), AddIncomeSourceRepository {
    override suspend fun fetchCategories() =
        Json.decodeFromString<List<String>>(remoteConfig.getString("categories"))
}