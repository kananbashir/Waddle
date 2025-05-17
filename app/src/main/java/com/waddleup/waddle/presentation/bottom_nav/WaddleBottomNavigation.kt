package com.waddleup.waddle.presentation.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.waddleup.core.navigation.destinations.bottom_nav.BottomNavDestination
import com.waddleup.theme.WaddleTheme

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Composable
fun WaddleBottomNavigation(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigateToDestination: (BottomNavDestination) -> Unit
) {
    val colors = WaddleTheme.colors

    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(colors.primaryBackground)
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        val itemWidth = rememberSaveable { screenWidth / BottomNavDestination.items.size }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavDestination.items.forEach { destination ->
                val currentRoute = destination.route::class.qualifiedName
                val isSelected = currentDestination
                    ?.hierarchy?.any { it.route == currentRoute } == true

                if (currentRoute == BottomNavDestination.Create.qualifiedRoute) {
                    Box(
                        modifier = Modifier.width(itemWidth.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(colors.primaryButton)
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = null,
                                        indication = null
                                    ) { onNavigateToDestination(destination) },
                                painter = painterResource(destination.iconResId),
                                contentDescription = "Create",
                                tint = colors.primaryBackground
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .width(itemWidth.dp)
                            .clickable(
                                interactionSource = null,
                                indication = null
                            ) { onNavigateToDestination(destination) }
                    ) {
                        Icon(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            painter = painterResource(destination.iconResId),
                            contentDescription = stringResource(destination.labelResId),
                            tint = if (isSelected) colors.activeBottomNavItem
                            else colors.nonActiveBottomNavItem
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(destination.labelResId),
                            style = WaddleTheme.typography.overlineMedium.Poppins,
                            color = if (isSelected) colors.activeBottomNavItem
                            else colors.nonActiveBottomNavItem,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WaddleBottomNavigationPreview() {
    WaddleBottomNavigation(
        currentDestination = null,
        onNavigateToDestination = {}
    )
}
