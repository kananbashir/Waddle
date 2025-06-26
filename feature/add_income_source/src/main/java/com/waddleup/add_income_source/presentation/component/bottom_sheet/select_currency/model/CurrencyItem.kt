package com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@Immutable
data class CurrencyItem(
    val id: Int,
    @DrawableRes val flagImage: Int,
    val currencyName: String
)
