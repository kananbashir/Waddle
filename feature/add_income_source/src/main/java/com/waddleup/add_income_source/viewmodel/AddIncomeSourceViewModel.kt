package com.waddleup.add_income_source.viewmodel

import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceIntent
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

class AddIncomeSourceViewModel(
    dispatchersProvider: DispatchersProvider
): BaseViewModel<AddIncomeSourceState, AddIncomeSourceIntent>(dispatchersProvider) {
    override val initialState: AddIncomeSourceState
        get() = AddIncomeSourceState()

    override fun onIntent(intent: AddIncomeSourceIntent) {
        when (intent) {
            is AddIncomeSourceIntent.IncomeSourceChanged -> updateIncomeSource(intent.value, intent.sourceIndex)
            is AddIncomeSourceIntent.IncomeAmountChanged -> updateIncomeAmount(intent.value, intent.sourceIndex)
            is AddIncomeSourceIntent.CurrencySelected -> {}
        }
    }

    private fun updateIncomeSource(value: String, sourceIndex: Int) {
        val expenseSource = currentUiStateData?.expenseSourceList?.getOrNull(sourceIndex)
        expenseSource?.let {
            currentUiStateData?.expenseSourceList?.set(
                sourceIndex,
                expenseSource.copy(
                    incomeSource = value
                )
            )
        }
    }

    private fun updateIncomeAmount(value: String, sourceIndex: Int) {
        val expenseSource = currentUiStateData?.expenseSourceList?.getOrNull(sourceIndex)
        expenseSource?.let {
            currentUiStateData?.expenseSourceList?.set(
                sourceIndex,
                expenseSource.copy(
                    incomeAmount = value
                )
            )
        }
    }

    private fun updateCurrency(value: String, sourceIndex: Int) {
        val expenseSource = currentUiStateData?.expenseSourceList?.getOrNull(sourceIndex)
        expenseSource?.let {
            currentUiStateData?.expenseSourceList?.set(
                sourceIndex,
                expenseSource.copy(
                    currency = value
                )
            )
        }
    }
}