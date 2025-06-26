package com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.content.SelectCurrencyBottomSheetContent
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.model.CurrencyItem
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.bottom_sheet.WaddleMainBottomSheet
import com.waddleup.core.presentation.components.bottom_sheet.model.BottomSheetAction
import kotlinx.collections.immutable.persistentListOf

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCurrencyBottomSheet(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    sheetState: SheetState,
    onNextClicked: () -> Unit,
    onDismiss: (() -> Unit)? = null,
    onBackClicked: () -> Unit,
    onCurrencyClicked: (Int) -> Unit
) {
    WaddleMainBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismiss = onDismiss,
        content = {
            SelectCurrencyBottomSheetContent(
                currencies = state.currencies,
                selectedCurrencyId = state.selectedCurrencyId,
                onBackIconClicked = onBackClicked,
                onCurrencyClicked = onCurrencyClicked
            )
        },
        positiveBottomSheetAction = BottomSheetAction(
            label = R.string.button_next,
            action = onNextClicked
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SelectCurrencyBottomSheetPreview() {
    SelectCurrencyBottomSheet(
        state = AddIncomeSourceState(
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
        ),
        sheetState = rememberModalBottomSheetState(),
        onNextClicked = {},
        onDismiss = {},
        onBackClicked = {},
        onCurrencyClicked = {},
    )
}