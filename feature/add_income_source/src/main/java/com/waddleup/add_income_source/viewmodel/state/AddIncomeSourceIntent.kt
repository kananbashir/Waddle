package com.waddleup.add_income_source.viewmodel.state

import androidx.annotation.DrawableRes

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

sealed class AddIncomeSourceIntent {
    data class IncomeSourceChanged(val value: String): AddIncomeSourceIntent()
    data class IncomeAmountChanged(val value: String): AddIncomeSourceIntent()
    data class CurrencySelected(val value: String, @DrawableRes val icon: Int): AddIncomeSourceIntent()
    data object AddNewExpenseCategoryClicked: AddIncomeSourceIntent()
    data class EditExpenseCategoryClicked(val index: Int): AddIncomeSourceIntent()
}