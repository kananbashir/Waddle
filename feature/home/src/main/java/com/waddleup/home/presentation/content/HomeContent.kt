package com.waddleup.home.presentation.content

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.components.content.WaddleMainContentWrapper
import com.waddleup.core.presentation.components.info_card.PrimaryInfoCard
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.home.R
import com.waddleup.home.presentation.components.DailyLimitText
import com.waddleup.home.presentation.components.DailyLimitTitle
import com.waddleup.home.presentation.components.HomeScreenTopBar
import com.waddleup.home.presentation.components.MascotGreetingImage
import com.waddleup.home.presentation.components.TransactionHistoryBoxDragHandler
import com.waddleup.home.presentation.components.TransactionHistoryHeader
import com.waddleup.home.util.HomeScreenNestedScrollConnection
import com.waddleup.home.viewmodel.state.HomeIntent
import com.waddleup.home.viewmodel.state.HomeState
import com.waddleup.navigation.notifications.NotificationsDestinations
import com.waddleup.theme.WaddleTheme
import timber.log.Timber

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

@OptIn(ExperimentalMotionApi::class)
@Composable
fun HomeContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
    onEvent: (UiEvent) -> Unit
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    var savedProgress by rememberSaveable { mutableFloatStateOf(0f) }
    val progressAnim = remember { Animatable(savedProgress) }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(progressAnim.value) {
        savedProgress = progressAnim.value
    }

    val dragRange = with(LocalDensity.current) { 300.dp.toPx() }
    val scope = rememberCoroutineScope()
    val snapThreshold = 0.5f
    val nested = remember(lazyListState, progressAnim.value) {
        HomeScreenNestedScrollConnection(progressAnim, lazyListState, dragRange, snapThreshold, scope)
    }

    WaddleMainContentWrapper(
        paddingValues = PaddingValues(),
        backgroundColor = WaddleTheme.colors.secondaryBackground,
        includeBottomNavPadding = true,
        topBar = {
            HomeScreenTopBar(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                onQuestionsClicked = {},
                onNotificationsClicked = {
                    onEvent(UiEvent.Navigate(NotificationsDestinations.NotificationsRoot))
                }
            )
        },
        content = {
            MotionLayout(
                modifier = Modifier.fillMaxSize(),
                motionScene = MotionScene(motionScene),
                progress = progressAnim.value
            ) {
                DailyLimitTitle(
                    modifier = Modifier.layoutId("daily_limit_title")
                )

                DailyLimitText(
                    modifier = Modifier.layoutId("daily_limit_text"),
                    state = state,
                    progress = progressAnim.value
                )

                MascotGreetingImage(
                    modifier = Modifier.layoutId("mascot")
                )

                TransactionHistoryHeader(
                    modifier = Modifier.layoutId("transaction_history_header"),
                    state = state
                )

                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .layoutId("transaction_history_box")
                        .nestedScroll(nested)
                ) {
                    VerticalSpacer(10.dp)

                    TransactionHistoryBoxDragHandler()

                    VerticalSpacer(10.dp)

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "History",
                            style = WaddleTheme.typography.body1Medium.Poppins,
                            color = WaddleTheme.colors.primaryText
                        )

                        Text(
                            text = "See all",
                            style = WaddleTheme.typography.captionRegular.Poppins,
                            color = WaddleTheme.colors.primaryText
                        )
                    }

                    VerticalSpacer(16.dp)

                    PrimaryInfoCard(
                        title = "Wow, you nailed it buddy \uD83D\uDD25",
                        subtitle = "For more details analize your budget "
                    )

                    VerticalSpacer(16.dp)

                    LazyColumn(
                        state = lazyListState
                    ) {
                        items(state.transactions) {
                            key(it.id) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp, horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row {
                                        Image(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(CircleShape),
                                            painter = painterResource(id = it.image),
                                            contentDescription = it.title,
                                            contentScale = ContentScale.Crop
                                        )

                                        HorizontalSpacer(12.dp)

                                        Column {
                                            Text(
                                                text = it.title,
                                                style = WaddleTheme.typography.body2Regular.Poppins,
                                                color = WaddleTheme.colors.primaryText
                                            )

                                            VerticalSpacer(4.dp)

                                            Row(
                                                modifier = Modifier
                                                    .background(
                                                        color = WaddleTheme.colors.inputFieldBackground,
                                                        shape = RoundedCornerShape(50.dp)
                                                    )
                                                    .padding(vertical = 4.dp, horizontal = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(6.dp)
                                                        .background(
                                                            color = it.category.color,
                                                            shape = CircleShape
                                                        )
                                                )

                                                Text(
                                                    text = it.category.categoryName,
                                                    style = WaddleTheme.typography.overline2Regular.Poppins,
                                                    color = WaddleTheme.colors.primaryText
                                                )
                                            }
                                        }
                                    }

                                    Column(
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "${it.amount}${it.currency}",
                                            style = WaddleTheme.typography.body2Medium.Poppins,
                                            color = WaddleTheme.colors.primaryText
                                        )

                                        Text(
                                            text = "${it.issueDate} • ${it.issueTime}",
                                            style = WaddleTheme.typography.overlineMedium.Poppins,
                                            color = WaddleTheme.colors.quaternaryText
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}