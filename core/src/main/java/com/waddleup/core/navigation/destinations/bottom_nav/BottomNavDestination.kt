package com.waddleup.core.navigation.destinations.bottom_nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.waddleup.core.R
import com.waddleup.core.navigation.destinations.ai_surprise.AiSurpriseDestinations
import com.waddleup.core.navigation.destinations.create.CreateDestinations
import com.waddleup.core.navigation.destinations.home.HomeDestinations
import com.waddleup.core.navigation.destinations.settings.SettingsDestinations
import com.waddleup.core.navigation.destinations.statistics.StatisticsDestinations

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
sealed class BottomNavDestination(
    val route: Any,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    val qualifiedRoute get() = this.route::class.qualifiedName

    data object Home: BottomNavDestination(
        route = HomeDestinations.HomeRoot,
        labelResId = R.string.bottom_nav_item_home,
        iconResId = R.drawable.ic_bottom_nav_home,
    )

    data object Statistics: BottomNavDestination(
        route = StatisticsDestinations.StatisticsRoot,
        labelResId = R.string.bottom_nav_item_statistics,
        iconResId = R.drawable.ic_bottom_nav_statistics,
    )

    data object Create: BottomNavDestination(
        route = CreateDestinations.Create,
        labelResId = -1,
        iconResId = R.drawable.ic_bottom_nav_create,
    )

    data object AiSurprise: BottomNavDestination(
        route = AiSurpriseDestinations.AiSurpriseRoot,
        labelResId = R.string.bottom_nav_item_ai_surprise,
        iconResId = R.drawable.ic_bottom_nav_ai_surprise,
    )

    data object Settings: BottomNavDestination(
        route = SettingsDestinations.SettingsRoot,
        labelResId = R.string.bottom_nav_item_settings,
        iconResId = R.drawable.ic_bottom_nav_settings,
    )

    companion object {
        val items = listOf(Home, Statistics, Create, AiSurprise, Settings)

        fun containsDestination(destination: NavDestination?): Boolean {
            return destination?.let { dest ->
                items.any { bottomNavItem ->
                    dest.hierarchy.any { navDest ->
                        navDest.route == bottomNavItem.route::class.qualifiedName
                    }
                }
            } ?: false
        }
    }
}