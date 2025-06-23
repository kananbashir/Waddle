package com.waddleup.add_income_source.presentation.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.presentation.component.AddIncomeSourceBottomBar
import com.waddleup.add_income_source.presentation.component.AddIncomeSourceTopBar
import com.waddleup.add_income_source.presentation.content.pager.MonthlyIncomeSourcesPage
import com.waddleup.add_income_source.presentation.model.AddIncomeSourcePage
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceIntent
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

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
                        MonthlyIncomeSourcesPage(
                            state = state,
                            onIncomeSourceUpdated = { s, i -> onIntent(AddIncomeSourceIntent.IncomeSourceChanged(s, i)) },
                            onIncomeAmountUpdated = { s, i -> onIntent(AddIncomeSourceIntent.IncomeAmountChanged(s, i)) },
                            onCurrencyClicked = { /* Show bottom sheet */ },
                        )
                    }
                    AddIncomeSourcePage.FixedExpenses.ordinal -> {}
                    AddIncomeSourcePage.FinancialGoal.ordinal -> {}
                    AddIncomeSourcePage.Success.ordinal -> {}
                }
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