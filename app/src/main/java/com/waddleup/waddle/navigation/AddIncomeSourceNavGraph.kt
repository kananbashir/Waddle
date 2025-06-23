package com.waddleup.waddle.navigation

import androidx.navigation.NavGraphBuilder
import com.waddleup.add_income_source.presentation.content.AddIncomeSourceContent
import com.waddleup.add_income_source.viewmodel.AddIncomeSourceViewModel
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceIntent
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.content.BaseScreen
import com.waddleup.navigation.add_income_source.AddIncomeSourceDestination
import com.waddleup.navigation.add_income_source.AddIncomeSourceRootDestination
import com.waddleup.waddle.navigation.util.waddleComposable
import com.waddleup.waddle.navigation.util.waddleNavigation

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.addIncomeSourceNavGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    waddleNavigation<AddIncomeSourceRootDestination>(AddIncomeSourceDestination) {
        waddleComposable<AddIncomeSourceDestination> {
            BaseScreen<AddIncomeSourceState, AddIncomeSourceIntent, AddIncomeSourceViewModel>(
                onUiEvent = onUiEvent,
                content = { viewModel, state ->
                    AddIncomeSourceContent(
                        state = state.value,
                        onIntent = viewModel::onIntent,
                        onEvent = viewModel::sendEvent
                    )
                }
            )
        }
    }
}