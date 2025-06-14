package com.waddleup.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.waddleup.core.presentation.components.other.DragHandler
import com.waddleup.home.R
import com.waddleup.home.viewmodel.state.HomeState
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
    onQuestionsClicked: () -> Unit,
    onNotificationsClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.placeholder_profile_image),
                contentDescription = "Profile image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = stringResource(id = R.string.home_top_bar_say_hi, "Kanan"),
                    style = WaddleTheme.typography.body2Medium.Poppins,
                    color = WaddleTheme.colors.text.tertiary
                )

                Text(
                    text = stringResource(id = R.string.home_top_bar_welcome_back),
                    style = WaddleTheme.typography.overlineMedium.Poppins,
                    color = WaddleTheme.colors.text.tertiary
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) { onQuestionsClicked() },
                painter = painterResource(id = R.drawable.ic_questions),
                contentDescription = "Questions",
                tint = WaddleTheme.colors.icons.secondaryTint
            )

            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) { onNotificationsClicked() },
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "Notifications",
                tint = WaddleTheme.colors.icons.secondaryTint
            )
        }
    }
}

@Composable
fun DailyLimitTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.text_your_daily_limit),
        style = WaddleTheme.typography.body2Regular.Poppins,
        color = WaddleTheme.colors.text.tertiary
    )
}

@Composable
fun DailyLimitText(
    modifier: Modifier = Modifier,
    state: HomeState,
    progress: Float
) {
    val textSize = 48f - (34f * progress)

    Text(
        modifier = modifier,
        text = "${state.dailyLimit ?: 0.0}€",
        style = WaddleTheme.typography.headline1Bold.Poppins,
        fontSize = textSize.sp,
        color = WaddleTheme.colors.text.tertiary
    )
}

@Composable
fun MascotGreetingImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.mascot_greeting),
        contentDescription = "Mascot greeting",
        alignment = Alignment.TopEnd
    )
}

@Composable
fun TransactionHistoryHeader(
    modifier: Modifier = Modifier,
    state: HomeState
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .background(
                color = WaddleTheme.colors.dividers.secondary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp
                )
            )
            .padding(top = 1.dp)
            .background(
                color = WaddleTheme.colors.containers.secondaryBackground.copy(alpha = 0.6f),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp
                )
            )
    ) {
        TransactionHistoryHeaderRowItem(
            rowScope = this,
            titleText = "May Savings:",
            descText = "+ € ${state.totalSaveOfMonth ?: 0.0}"
        )

        VerticalDivider(
            modifier = Modifier
                .padding(vertical = 8.dp),
            thickness = 1.dp,
            color = WaddleTheme.colors.dividers.secondary.copy(alpha = 0.1f)
        )

        TransactionHistoryHeaderRowItem(
            rowScope = this,
            titleText = "May Spending:",
            descText = "+ € ${state.totalSpendingOfTheMonth ?: 0.0}"
        )
    }
}

@Composable
fun TransactionHistoryHeaderRowItem(
    modifier: Modifier = Modifier,
    rowScope: RowScope,
    titleText: String,
    descText: String
) {
    rowScope.apply {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(vertical = 11.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleText,
                style = WaddleTheme.typography.captionSemiBold.Poppins,
                color = WaddleTheme.colors.containers.secondaryText
            )

            Text(
                text = descText,
                style = WaddleTheme.typography.captionSemiBold.Poppins,
                color = WaddleTheme.colors.containers.secondaryText
            )
        }
    }
}

@Composable
fun TransactionHistoryBoxDragHandler(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        DragHandler()
    }
}