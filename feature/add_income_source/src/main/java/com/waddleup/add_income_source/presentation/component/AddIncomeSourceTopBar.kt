package com.waddleup.add_income_source.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

@Composable
fun AddIncomeSourceTopBar(
    modifier: Modifier = Modifier
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        VerticalSpacer(16.dp)

        Text(
            text = stringResource(R.string.add_income_source_header_1),
            style = types.headline2Bold.PlusJakarta,
            color = colors.text.primary
        )

        VerticalSpacer(8.dp)

        Text(
            text = stringResource(R.string.add_income_source_body_1),
            style = types.body2Medium.PlusJakarta,
            color = colors.text.primary
        )

        VerticalSpacer(16.dp)
    }
}