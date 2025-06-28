package com.waddleup.add_income_source.viewmodel.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.model.CurrencyItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@Immutable
data class AddIncomeSourceState(
    val isLoading: Boolean = false,
    val currentPage: Int = 0,
    val totalAmount: Double = 0.0,
    val incomeSource: String = "",
    val incomeSourceError: String = "",
    val incomeAmount: String = "",
    val incomeAmountError: String = "",
    val currency: String = "",
    val currencyFlag: Int? = null,
    val currencyError: String? = "",
    val editingIndex: Int? = null,
    val savedExpenseSourceList: SnapshotStateList<ExpenseSource> = mutableStateListOf(),
    val selectedCurrencyId: Int? = null,
    val currencies: PersistentList<CurrencyItem> = persistentListOf(),
    val fixedExpenseCategorySearch: String = "",
    val categorySuggestion: String? = null
) {
    @Immutable
    data class ExpenseSource(
        val incomeSource: String = "",
        val incomeSourceError: String = "",
        val incomeAmount: String = "",
        val incomeAmountError: String = "",
        val currency: String = "",
        val currencyError: String? = "",
        val currencyFlag: Int?
    )
}