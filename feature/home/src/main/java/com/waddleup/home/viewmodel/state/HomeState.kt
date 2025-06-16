package com.waddleup.home.viewmodel.state

import androidx.compose.runtime.Immutable
import com.waddleup.home.domain.Transaction
import kotlinx.collections.immutable.PersistentList

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

@Immutable
data class HomeState(
    val dailyLimit: Double? = null,
    val totalSaveOfMonth: Double? = null,
    val totalSpendingOfTheMonth: Double? = null,
    val isLoading: Boolean = false,
    val transactions: PersistentList<Transaction>? = null
)
