package com.waddleup.core.presentation.components.info_box

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/15/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleInfoBoxWrapper(
    modifier: Modifier = Modifier,
    shape: Shape = WaddleTheme.shapes.small,
    headerContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
    bottomActionContent: @Composable (() -> Unit)? = null,
    trailingIconContent: @Composable (() -> Unit)? = null,
) {
    val colors = WaddleTheme.colors
    val shapes = WaddleTheme.shapes
    val types = WaddleTheme.typography

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(shape)
            .drawWithCache {
                onDrawBehind {
                    drawRoundRect(
                        color = colors.infoBoxes.primaryBackground,
                    )

                    drawRoundRect(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colors.infoBoxes.primaryForeground.copy(alpha = 0.5f),
                                colors.infoBoxes.primaryForeground2.copy(alpha = 0.5f),
                            )
                        )
                    )
                }
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(),
        ){
            Image(
                modifier = Modifier
                    .fillMaxWidth(bottomActionContent?.let { 0.37f } ?: 0.22f)
                    .fillMaxHeight(),
                painter = painterResource(R.drawable.mascot_with_money),
                contentDescription = null,
                alignment = Alignment.BottomEnd
            )

            trailingIconContent?.let {
                HorizontalSpacer(4.dp)
                it()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(
                        fraction = 0.7f
                    ),
            ) {
                headerContent()
                VerticalSpacer(4.dp)
                bodyContent()
                VerticalSpacer(16.dp)

                bottomActionContent?.let {
                    VerticalSpacer(4.dp)
                    it()
                }
            }
        }
    }
}

@Preview
@Composable
private fun WaddleInfoBoxWrapperPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = (16.5).dp, vertical = 32.dp),
    ) {
        WaddleMainInfoBox(
            headerText = R.string.settings_info_box_header,
            bodyText = R.string.settings_info_box_body,
            buttonText = R.string.button_see_more,
            trailingIcon = R.drawable.ic_error,
            onButtonClicked = {},
            onCloseClicked = {}
        )
    }
}