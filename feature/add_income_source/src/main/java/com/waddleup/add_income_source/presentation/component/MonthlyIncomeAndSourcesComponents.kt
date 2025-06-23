package com.waddleup.add_income_source.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
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
    expenseSource: AddIncomeSourceState.ExpenseSource,
    columnScope: ColumnScope,
    onIncomeSourceUpdated: (String) -> Unit,
    onIncomeAmountUpdated: (String) -> Unit,
    onCurrencyClicked: () -> Unit
) {
    columnScope.apply {
        VerticalSpacer(32.dp)

        IncomeSourceTextField(
            expenseSource = expenseSource,
            onValueChange = onIncomeSourceUpdated
        )

        VerticalSpacer(8.dp)

        IncomeAmountTextField(
            expenseSource = expenseSource,
            onValueChange = onIncomeAmountUpdated
        )

        VerticalSpacer(8.dp)

        CurrencyTextField(
            expenseSource = expenseSource,
            onClicked = onCurrencyClicked
        )
    }
}

@Composable
private fun IncomeSourceTextField(
    modifier: Modifier = Modifier,
    expenseSource: AddIncomeSourceState.ExpenseSource,
    onValueChange: (String) -> Unit
) {
    WaddleSecondaryTextField(
        modifier = modifier,
        value = expenseSource.incomeSource,
        onValueChange = onValueChange,
        placeholderText = stringResource(R.string.text_income_source),
        errorMessage = expenseSource.incomeSourceError,
        leadingIconRes = R.drawable.ic_suitcase,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
private fun IncomeAmountTextField(
    modifier: Modifier = Modifier,
    expenseSource: AddIncomeSourceState.ExpenseSource,
    onValueChange: (String) -> Unit
) {
    WaddleSecondaryTextField(
        modifier = modifier,
        value = expenseSource.incomeAmount,
        onValueChange = onValueChange,
        placeholderText = stringResource(R.string.text_income_amount),
        errorMessage = expenseSource.incomeAmountError,
        leadingIconRes = R.drawable.ic_money_cash,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
private fun CurrencyTextField(
    modifier: Modifier = Modifier,
    expenseSource: AddIncomeSourceState.ExpenseSource,
    onClicked: () -> Unit
) {
    WaddleSecondaryTextField(
        modifier = modifier
            .clickable(
                interactionSource = null,
                indication = ripple(
                    bounded = true,
                    color = WaddleTheme.colors.buttons.primary
                )
            ) { onClicked() },
        value = expenseSource.currency,
        onValueChange = {},
        placeholderText = stringResource(R.string.text_currency),
        errorMessage = expenseSource.currencyError,
        unspecifiedLeadingIconColor = expenseSource.currencyLeadingIcon != R.drawable.ic_money_cash_repeat,
        leadingIconRes = expenseSource.currencyLeadingIcon,
        trailingIconRes = R.drawable.ic_down_arrow,
        colors = TextFieldDefaults.secondaryWaddleColors().copy(
            disabledLeadingIconColor = WaddleTheme.colors.inputFields.primaryText,
            disabledTrailingIconColor = WaddleTheme.colors.inputFields.primaryText
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        enabled = false
    )
}

@Composable
fun AddNewExpenseActionText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

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
            text = stringResource(R.string.add_income_source_action_add_new_category),
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