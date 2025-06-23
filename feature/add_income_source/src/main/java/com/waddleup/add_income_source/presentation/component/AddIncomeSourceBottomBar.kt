package com.waddleup.add_income_source.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.button.WaddlePrimaryButton
import com.waddleup.core.presentation.components.button.WaddleSecondaryButton
import com.waddleup.core.presentation.components.button.tertiaryWaddleColors
import com.waddleup.core.presentation.components.other.HorizontalSpacer

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

@Composable
fun AddIncomeSourceBottomBar(
    modifier: Modifier = Modifier,
    onSkipClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SkipButton(
            modifier = Modifier
                .weight(1f),
            onSkipClicked = onSkipClicked
        )

        HorizontalSpacer(22.dp)

        NextButton(
            modifier = Modifier
                .weight(1f),
            onNextClicked = onNextClicked
        )
    }
}

@Composable
private fun SkipButton(
    modifier: Modifier = Modifier,
    onSkipClicked: () -> Unit
) {
    WaddleSecondaryButton(
        modifier = modifier,
        buttonText = stringResource(R.string.button_skip),
        colors = ButtonDefaults.tertiaryWaddleColors(),
        onClick = onSkipClicked
    )
}

@Composable
private fun NextButton(
    modifier: Modifier = Modifier,
    onNextClicked: () -> Unit
) {
    WaddlePrimaryButton(
        modifier = modifier,
        buttonText = stringResource(R.string.button_next),
        onClick = onNextClicked
    )
}