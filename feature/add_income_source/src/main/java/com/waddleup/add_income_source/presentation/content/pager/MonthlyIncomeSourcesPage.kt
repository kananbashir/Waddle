package com.waddleup.add_income_source.presentation.content.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.presentation.component.AddNewExpenseActionText
import com.waddleup.add_income_source.presentation.component.MonthlyIncomeAndSourcesTextFields
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

@Composable
fun MonthlyIncomeSourcesPage(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onIncomeSourceUpdated: (String, Int) -> Unit,
    onIncomeAmountUpdated: (String, Int) -> Unit,
    onCurrencyClicked: (Int) -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography
    val shapes = WaddleTheme.shapes
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            state.expenseSourceList.forEachIndexed { index, expenseSource ->
                MonthlyIncomeAndSourcesTextFields(
                    expenseSource = expenseSource,
                    onIncomeSourceUpdated = { onIncomeSourceUpdated(it, index) },
                    onIncomeAmountUpdated = { onIncomeAmountUpdated(it, index) },
                    onCurrencyClicked = { onCurrencyClicked(index) },
                    columnScope = this
                )
            }

            AddNewExpenseActionText(
                onClick = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shapes.small)
                .background(colors.infoBoxes.secondaryBackground)
                .padding(vertical = 16.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_total_amount_with_colon),
                style = types.body1Medium.PlusJakarta,
                color = colors.infoBoxes.onSecondaryBackground
            )

            HorizontalSpacer(4.dp)

            Text(
                text = state.totalAmount.toString(),
                style = types.body1Medium.PlusJakarta,
                color = colors.infoBoxes.onSecondaryBackground
            )
        }
    }

}

@Preview
@Composable
private fun MonthlyIncomeSourcesPagePreview() {
    Box(
        modifier = Modifier
            .background(WaddleTheme.colors.background.primary)
    ) {
        MonthlyIncomeSourcesPage(
            state = AddIncomeSourceState(),
            onIncomeSourceUpdated = { _, _ -> },
            onIncomeAmountUpdated = { _, _ -> },
            onCurrencyClicked = {}
        )
    }
}