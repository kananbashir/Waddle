package com.waddleup.core.presentation.components.content

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.waddleup.core.R
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleMainTopBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onBackClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) { onBackClicked() },
                painter = painterResource(id = R.drawable.ic_left_arrow),
                contentDescription = "Password recovery back button",
                tint = WaddleTheme.colors.primaryButtonTint
            )
        }

        Text(
            modifier = Modifier.width(IntrinsicSize.Max),
            text = stringResource(title),
            style = WaddleTheme.typography.body1Medium.Poppins,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}