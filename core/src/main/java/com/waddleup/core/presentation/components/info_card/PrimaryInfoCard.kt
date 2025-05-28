package com.waddleup.core.presentation.components.info_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.waddleup.core.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/28/2025
 * @author Kanan Bashir
 */

@Composable
fun PrimaryInfoCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 16.dp)
            .background(
                color = WaddleTheme.colors.tertiaryContainerBackground,
                shape = WaddleTheme.shapes.small
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mascot_eye_love),
//            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            alignment = Alignment.BottomStart
        )

        HorizontalSpacer(10.dp)

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = title,
                    style = WaddleTheme.typography.body2Medium.Poppins,
                    color = WaddleTheme.colors.primaryText
                )

                Text(
                    text = subtitle,
                    style = WaddleTheme.typography.captionLight.Poppins,
                    color = WaddleTheme.colors.primaryText
                )
            }
        }

        Icon(
            modifier = Modifier
                .padding(vertical = 20.dp),
            painter = painterResource(id = R.drawable.ic_right_arrow),
//            imageVector = Icons.Default.Build,
            contentDescription = null
        )

        HorizontalSpacer(24.dp)
    }
}