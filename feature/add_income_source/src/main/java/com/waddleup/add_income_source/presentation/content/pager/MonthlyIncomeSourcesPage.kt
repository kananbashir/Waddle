package com.waddleup.add_income_source.presentation.content.pager

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.presentation.component.first_page_component.AddNewExpenseActionText
import com.waddleup.add_income_source.presentation.component.first_page_component.TotalAmountInfoBox
import com.waddleup.add_income_source.presentation.component.first_page_component.ExpenseCategoryItem
import com.waddleup.add_income_source.presentation.component.first_page_component.MonthlyIncomeAndSourcesTextFields
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState.ExpenseSource
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

@Composable
fun MonthlyIncomeSourcesPage(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onIncomeSourceUpdated: (String) -> Unit,
    onIncomeAmountUpdated: (String) -> Unit,
    onCurrencyClicked: () -> Unit,
    onAddNewExpenseCategoryClicked: () -> Unit,
    onEditClicked: (Int) -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography
    val shapes = WaddleTheme.shapes
    val density = LocalDensity.current
    var totalAmountBoxHeight by remember { mutableIntStateOf(0) }
    var totalAmountBoxHeightDp = remember(totalAmountBoxHeight) {
        with(density) { totalAmountBoxHeight.dp }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            item { VerticalSpacer(16.dp) }

            itemsIndexed(
                state.savedExpenseSourceList,
                key = { i, s -> s.incomeSource }
            ) { index, savedExpenseSource ->
                ExpenseCategoryItem(
                    savedExpenseSource = savedExpenseSource,
                    onEditClicked = { onEditClicked(index) }
                )
            }

            item {
                MonthlyIncomeAndSourcesTextFields(
                    state = state,
                    onIncomeSourceUpdated = { onIncomeSourceUpdated(it) },
                    onIncomeAmountUpdated = { onIncomeAmountUpdated(it) },
                    onCurrencyClicked = { onCurrencyClicked() }
                )
            }

            item {
                AddNewExpenseActionText(
                    state = state,
                    onClick = onAddNewExpenseCategoryClicked
                )
            }

            item { VerticalSpacer(totalAmountBoxHeightDp + 16.dp) }
        }

        TotalAmountInfoBox(
            state = state,
            onHeightCalculated = { totalAmountBoxHeight = it }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun MonthlyIncomeSourcesPagePreview() {
    Box(
        modifier = Modifier
            .background(WaddleTheme.colors.background.primary)
    ) {
        MonthlyIncomeSourcesPage(
            state = AddIncomeSourceState(
                savedExpenseSourceList = mutableStateListOf(
                    ExpenseSource(
                        incomeSource = "Waddle company",
                        incomeAmount = "1500",
                        currency = "USD"
                    ),
                    ExpenseSource(
                        incomeSource = "Waddle company 2",
                        incomeAmount = "1500",
                        currency = "USD"
                    )
                )
            ),
            onIncomeSourceUpdated = {},
            onIncomeAmountUpdated = {},
            onCurrencyClicked = {},
            onAddNewExpenseCategoryClicked = {},
            onEditClicked = {}
        )
    }
}