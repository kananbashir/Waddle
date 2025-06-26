package com.waddleup.add_income_source.viewmodel

import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.model.CurrencyItem
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceIntent
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import kotlinx.collections.immutable.persistentListOf

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

class AddIncomeSourceViewModel(
    dispatchersProvider: DispatchersProvider
): BaseViewModel<AddIncomeSourceState, AddIncomeSourceIntent>(dispatchersProvider) {
    override val initialState: AddIncomeSourceState
        get() = AddIncomeSourceState()

    init {
        setState {
            it.copy(
                currencies = persistentListOf(
                    CurrencyItem(
                        id = 1,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "AZN"
                    ),
                    CurrencyItem(
                        id = 2,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "USD"
                    ),
                    CurrencyItem(
                        id = 3,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "EUR"
                    ),
                    CurrencyItem(
                        id = 4,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "RUB"
                    ),
                    CurrencyItem(
                        id = 5,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "TRY"
                    ),
                    CurrencyItem(
                        id = 6,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "GBP"
                    ),
                    CurrencyItem(
                        id = 7,
                        flagImage = R.drawable.ic_launcher_background,
                        currencyName = "AED"
                    )
                )
            )
        }
    }

    override fun onIntent(intent: AddIncomeSourceIntent) {
        when (intent) {
            is AddIncomeSourceIntent.IncomeSourceChanged -> updateIncomeSource(intent.value)
            is AddIncomeSourceIntent.IncomeAmountChanged -> updateIncomeAmount(intent.value)
            is AddIncomeSourceIntent.EditExpenseCategoryClicked -> editExpenseCategory(intent.index)
            is AddIncomeSourceIntent.AddNewExpenseCategoryClicked -> addNewExpenseCategory()
            is AddIncomeSourceIntent.CurrencyClicked -> setState { it.copy(selectedCurrencyId = intent.id) }
            AddIncomeSourceIntent.CurrencySelected -> selectCurrency()
        }
    }

    private fun selectCurrency() {
        uiState.value.apply {
            selectedCurrencyId?.let { id ->
                val currencyItem = currencies.find { it.id == id }

                currencyItem?.let {
                    setState {
                        it.copy(
                            currency = currencyItem.currencyName,
                            currencyFlag = currencyItem.flagImage
                        )
                    }
                }
            }
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
                    currency = currency,
                    currencyFlag = currencyFlag
                )
            )

            setState {
                it.copy(
                    incomeSource = "",
                    incomeAmount = "",
                    currency = "",
                    currencyFlag = null,
                    totalAmount = totalAmount,
                    editingIndex = null,
                    selectedCurrencyId = null
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