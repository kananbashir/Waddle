package com.waddleup.add_income_source.presentation.component.first_page_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.input.WaddleSecondaryTextField
import com.waddleup.core.presentation.components.input.util.secondaryWaddleColors
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

@Composable
fun MonthlyIncomeAndSourcesTextFields(
    state: AddIncomeSourceState,
    onIncomeSourceUpdated: (String) -> Unit,
    onIncomeAmountUpdated: (String) -> Unit,
    onCurrencyClicked: () -> Unit
) {
    Column {
        VerticalSpacer(32.dp)

        IncomeSourceTextField(
            state = state,
            onValueChange = onIncomeSourceUpdated
        )

        VerticalSpacer(8.dp)

        IncomeAmountTextField(
            state = state,
            onValueChange = onIncomeAmountUpdated
        )

        VerticalSpacer(8.dp)

        CurrencyTextField(
            state = state,
            onClicked = onCurrencyClicked
        )
    }
}

@Composable
private fun IncomeSourceTextField(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onValueChange: (String) -> Unit
) {
    WaddleSecondaryTextField(
        modifier = modifier,
        value = state.incomeSource,
        onValueChange = onValueChange,
        placeholderText = stringResource(R.string.text_income_source),
        errorMessage = state.incomeSourceError,
        leadingIconRes = R.drawable.ic_suitcase,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
private fun IncomeAmountTextField(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onValueChange: (String) -> Unit
) {
    WaddleSecondaryTextField(
        modifier = modifier,
        value = state.incomeAmount,
        onValueChange = onValueChange,
        placeholderText = stringResource(R.string.text_income_amount),
        errorMessage = state.incomeAmountError,
        leadingIconRes = R.drawable.ic_money_cash,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
private fun CurrencyTextField(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onClicked: () -> Unit
) {
    WaddleSecondaryTextField(
        modifier = modifier
            .clip(WaddleTheme.shapes.small)
            .clickable(
                interactionSource = null,
                indication = ripple(
                    bounded = true,
                    color = WaddleTheme.colors.buttons.primary
                )
            ) { onClicked() },
        value = state.currency,
        onValueChange = {},
        placeholderText = stringResource(R.string.text_currency),
        errorMessage = state.currencyError,
        unspecifiedLeadingIconColor = state.currencyLeadingIcon != R.drawable.ic_money_cash_repeat,
        leadingIconRes = state.currencyLeadingIcon,
        trailingIconRes = R.drawable.ic_down_arrow,
        colors = TextFieldDefaults.secondaryWaddleColors().copy(
            disabledLeadingIconColor = WaddleTheme.colors.inputFields.primaryText,
            disabledTrailingIconColor = WaddleTheme.colors.inputFields.primaryText
        ),
        enabled = false
    )
}

@Composable
fun AddNewExpenseActionText(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onClick: () -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography
    val text = if (state.editingIndex != null) R.string.add_income_source_action_edit_new_category
    else R.string.add_income_source_action_add_new_category

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = null,
                indication = null
            ) { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add_plus),
            contentDescription = null,
            tint = colors.icons.primaryTint
        )

        HorizontalSpacer(8.dp)

        Text(
            text = stringResource(text),
            style = types.body2Medium.PlusJakarta,
            color = colors.icons.primaryTint
        )

        HorizontalSpacer(8.dp)

        Icon(
            painter = painterResource(R.drawable.ic_add_plus),
            contentDescription = null,
            tint = Color.Transparent
        )
    }
}