package com.waddleup.add_income_source.presentation.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.presentation.component.AddIncomeSourceBottomBar
import com.waddleup.add_income_source.presentation.component.AddIncomeSourceTopBar
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.SelectCurrencyBottomSheet
import com.waddleup.add_income_source.presentation.content.pager.FixedExpensesPage
import com.waddleup.add_income_source.presentation.model.AddIncomeSourcePage
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceIntent
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.core.presentation.util.hide
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddIncomeSourceContent(
    state: AddIncomeSourceState,
    onIntent: (AddIncomeSourceIntent) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { AddIncomeSourcePage.entries.size }
    )
    val scope = rememberCoroutineScope()
    val selectCurrencyBottomSheetState = rememberModalBottomSheetState(true)
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = state.currentPage) {
        pagerState.animateScrollToPage(state.currentPage)
    }

    WaddleMainContentWrapper(
        paddingValues = PaddingValues(),
        isLoading = state.isLoading,

        topBar = { AddIncomeSourceTopBar() },

        content = {
            HorizontalPager(
                userScrollEnabled = false,
                state = pagerState,
                pageSpacing = 16.dp,
                verticalAlignment = Alignment.Top
            ) {page ->
                when (page) {
                    AddIncomeSourcePage.MonthlyIncomeAndSources.ordinal -> {
                        FixedExpensesPage(
                            state = state,
                            onCategorySearchUpdated = { onIntent(AddIncomeSourceIntent.CategorySearchChanged(it)) }
                        )

//                        MonthlyIncomeSourcesPage(
//                            state = state,
//                            onIncomeSourceUpdated = { onIntent(AddIncomeSourceIntent.IncomeSourceChanged(it)) },
//                            onIncomeAmountUpdated = { onIntent(AddIncomeSourceIntent.IncomeAmountChanged(it)) },
//                            onCurrencyClicked = {
//                                keyboardController?.hide()
//                                selectCurrencyBottomSheetState.show(scope)
//                            },
//                            onAddNewExpenseCategoryClicked = { onIntent(AddIncomeSourceIntent.AddNewExpenseCategoryClicked) },
//                            onEditClicked = { onIntent(AddIncomeSourceIntent.EditExpenseCategoryClicked(it)) }
//                        )
                    }
                    AddIncomeSourcePage.FixedExpenses.ordinal -> {}
                    AddIncomeSourcePage.FinancialGoal.ordinal -> {}
                    AddIncomeSourcePage.Success.ordinal -> {}
                }
            }

            if (selectCurrencyBottomSheetState.isVisible) {
                SelectCurrencyBottomSheet(
                    state = state,
                    sheetState = selectCurrencyBottomSheetState,
                    onNextClicked = {
                        onIntent(AddIncomeSourceIntent.CurrencySelected)
                        selectCurrencyBottomSheetState.hide(scope)
                    },
                    onBackClicked = { selectCurrencyBottomSheetState.hide(scope) },
                    onCurrencyClicked = { onIntent(AddIncomeSourceIntent.CurrencyClicked(it)) },
                )
            }
        },

        bottomBar = {
            AddIncomeSourceBottomBar(
                onSkipClicked = {},
                onNextClicked = {}
            )
        }
    )
}

@Preview
@Composable
private fun AddIncomeSourcePreview() {
    AddIncomeSourceContent(
        state = AddIncomeSourceState(),
        onIntent = {},
        onEvent = {}
    )
}