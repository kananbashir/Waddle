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
            is AddIncomeSourceIntent.IncomeSourceChanged -> updateIncomeSource(intent.value)
            is AddIncomeSourceIntent.IncomeAmountChanged -> updateIncomeAmount(intent.value)
            is AddIncomeSourceIntent.CurrencySelected -> {}
            is AddIncomeSourceIntent.EditExpenseCategoryClicked -> editExpenseCategory(intent.index)
            is AddIncomeSourceIntent.AddNewExpenseCategoryClicked -> addNewExpenseCategory()
        }
    }

    private fun editExpenseCategory(index: Int) {
        uiState.value.apply {
            val item = savedExpenseSourceList.getOrNull(index)

            item?.let {
                val amount = if (it.incomeAmount.isNotEmpty()) it.incomeAmount.toDouble()
                else 0.0
                val totalAmount = totalAmount.minus(amount)

                savedExpenseSourceList.removeAt(index)

                setState {
                    it.copy(
                        incomeSource = item.incomeSource,
                        incomeAmount = item.incomeAmount,
                        currency = item.currency,
                        editingIndex = index,
                        totalAmount = totalAmount
                    )
                }
            }
        }
    }

    private fun addNewExpenseCategory() {
        uiState.value.apply {
            val amount = if (incomeAmount.isNotEmpty()) incomeAmount.toDouble()
            else 0.0
            val totalAmount = totalAmount.plus(amount)
            savedExpenseSourceList.add(
                editingIndex ?: savedExpenseSourceList.size,
                AddIncomeSourceState.ExpenseSource(
                    incomeSource = incomeSource,
                    incomeAmount = amount.toString(),
                    currency = currency
                )
            )

            setState {
                it.copy(
                    incomeSource = "",
                    incomeAmount = "",
                    currency = "",
                    totalAmount = totalAmount,
                    editingIndex = null
                )
            }
        }
    }

    private fun updateIncomeSource(value: String) {
        setState { it.copy(incomeSource = value) }
    }

    private fun updateIncomeAmount(value: String) {
        setState { it.copy(incomeAmount = value) }
    }

    private fun updateCurrency(value: String) {
        setState { it.copy(currency = value) }
    }
}