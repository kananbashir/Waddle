package com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.content.component.CurrencyItemRadio
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.content.component.SelectCurrencyTopSection
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.model.CurrencyItem
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@Composable
fun SelectCurrencyBottomSheetContent(
    modifier: Modifier = Modifier,
    currencies: PersistentList<CurrencyItem>,
    selectedCurrencyId: Int?,
    onBackIconClicked: () -> Unit,
    onCurrencyClicked: (Int) -> Unit
) {
    val colors = WaddleTheme.colors

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        SelectCurrencyTopSection(
            onBackIconClicked = onBackIconClicked
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = colors.dividers.quaternary
        )

        LazyColumn {
            item { VerticalSpacer(12.dp) }

            items(currencies, key = { it.id }) {
                CurrencyItemRadio(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    currencyItem = it,
                    selectedCurrencyId = selectedCurrencyId,
                    onCurrencyClicked = onCurrencyClicked
                )
            }

            item { VerticalSpacer(12.dp) }
        }
    }
}

@Preview
@Composable
private fun SelectCurrencyBottomSheetContentPreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        SelectCurrencyBottomSheetContent(
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
            ),
            selectedCurrencyId = 2,
            onBackIconClicked = {},
            onCurrencyClicked = {}
        )
    }
}