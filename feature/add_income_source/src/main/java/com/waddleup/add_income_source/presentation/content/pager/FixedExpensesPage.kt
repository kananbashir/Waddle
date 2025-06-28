package com.waddleup.add_income_source.presentation.content.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.input.WaddleMainTextField
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/27/2025
 * @author Kanan Bashir
 */

@Composable
fun FixedExpensesPage(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onCategorySearchUpdated: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        WaddleMainTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            value = state.fixedExpenseCategorySearch,
            onValueChange = onCategorySearchUpdated,
            titleText = null,
            placeholderText = stringResource(R.string.text_create),
            leadingIconRes = R.drawable.ic_search,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Did you mean?: ",
                style = WaddleTheme.typography.body1Medium.Poppins,
                color = WaddleTheme.colors.text.primary
            )

            Text(
                text = state.categorySuggestion ?: "-",
                style = WaddleTheme.typography.body1Medium.Poppins,
                color = WaddleTheme.colors.text.primary
            )
        }
    }
}

@Preview
@Composable
private fun FixedExpensesPagePreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        FixedExpensesPage(
            state = AddIncomeSourceState(),
            onCategorySearchUpdated = {}
        )
    }
}