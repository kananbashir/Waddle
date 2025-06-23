package com.waddleup.add_income_source.viewmodel.state

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.waddleup.app.theme.R

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Immutable
data class AddIncomeSourceState(
    val isLoading: Boolean = false,
    val currentPage: Int = 0,
    val totalAmount: Int = 0,
    val expenseSourceList: SnapshotStateList<ExpenseSource> = mutableStateListOf(
        ExpenseSource()
    )
) {
    @Immutable
    data class ExpenseSource(
        val incomeSource: String = "",
        val incomeSourceError: String = "",
        val incomeAmount: String = "",
        val incomeAmountError: String = "",
        val currency: String = "",
        val currencyError: String? = "",
        @DrawableRes val currencyLeadingIcon: Int = R.drawable.ic_money_cash_repeat
    )
}