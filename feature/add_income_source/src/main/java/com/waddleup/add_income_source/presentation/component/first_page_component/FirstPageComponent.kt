package com.waddleup.add_income_source.presentation.component.first_page_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/26/2025
 * @author Kanan Bashir
 */

@Composable
fun TotalAmountInfoBox(
    modifier: Modifier = Modifier,
    state: AddIncomeSourceState,
    onHeightCalculated: (Int) -> Unit
) {
    val colors = WaddleTheme.colors
    val types = WaddleTheme.typography
    val shapes = WaddleTheme.shapes
    var isCalculated by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.small)
            .background(colors.infoBoxes.secondaryBackground)
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .onSizeChanged {
                if (isCalculated.not()) {
                    onHeightCalculated(it.height)
                    isCalculated = true
                }
            },
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