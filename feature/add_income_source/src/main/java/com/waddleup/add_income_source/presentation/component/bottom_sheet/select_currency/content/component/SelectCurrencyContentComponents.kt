package com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.content.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.presentation.component.bottom_sheet.select_currency.model.CurrencyItem
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.core.presentation.components.radio_button.WaddleRadioButton
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@Composable
fun SelectCurrencyTopSection(
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .clickable(
                    interactionSource = null,
                    indication = null
                ) { onBackIconClicked() },
            painter = painterResource(R.drawable.ic_left_arrow),
            contentDescription = null,
            tint = colors.icons.quaternaryTint
        )

        Text(
            text = stringResource(R.string.select_currency_bottom_sheet_title),
            style = types.subtitle1Medium.Poppins,
            color = colors.text.primary
        )

        Icon(
            painter = painterResource(R.drawable.ic_left_arrow),
            contentDescription = null,
            tint = Color.Transparent
        )
    }
}

@Composable
fun CurrencyItemRadio(
    modifier: Modifier = Modifier,
    currencyItem: CurrencyItem,
    selectedCurrencyId: Int?,
    onCurrencyClicked: (Int) -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                painter = painterResource(currencyItem.flagImage),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            HorizontalSpacer(12.dp)

            Text(
                text = currencyItem.currencyName,
                style = types.body1Regular.PlusJakarta,
                color = colors.text.primary
            )
        }

        WaddleRadioButton(
            selected = selectedCurrencyId == currencyItem.id,
            onClick = { onCurrencyClicked(currencyItem.id) }
        )
    }
}