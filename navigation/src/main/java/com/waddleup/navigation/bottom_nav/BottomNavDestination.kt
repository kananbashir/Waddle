package com.waddleup.navigation.bottom_nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.waddleup.app.theme.R
import com.waddleup.navigation.ai_surprise.AiSurpriseRootDestination
import com.waddleup.navigation.create.CreateRootDestination
import com.waddleup.navigation.home.HomeRootDestinations
import com.waddleup.navigation.settings.SettingsRootDestination
import com.waddleup.navigation.statictics.StatisticsRootDestination

/**
 * Created on 5/8/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
sealed class BottomNavDestination(
    val route: Any,
    val startDestination: Any,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    val qualifiedRoute get() = this.route::class.qualifiedName

    data object Home: BottomNavDestination(
        route = HomeRootDestinations,
        startDestination = com.waddleup.navigation.home.HomeDestination,
        labelResId = R.string.text_home,
        iconResId = R.drawable.ic_bottom_nav_home,
    )

    data object Statistics: BottomNavDestination(
        route = StatisticsRootDestination,
        startDestination = com.waddleup.navigation.statictics.StatisticsDestination,
        labelResId = R.string.text_statistics,
        iconResId = R.drawable.ic_bottom_nav_statistics,
    )

    data object Create: BottomNavDestination(
        route = CreateRootDestination,
        startDestination = com.waddleup.navigation.create.CreateDestination,
        labelResId = -1,
        iconResId = R.drawable.ic_bottom_nav_create,
    )

    data object AiSurprise: BottomNavDestination(
        route = AiSurpriseRootDestination,
        startDestination = com.waddleup.navigation.ai_surprise.AiSurpriseDestination,
        labelResId = R.string.text_ai_surprise,
        iconResId = R.drawable.ic_bottom_nav_ai_surprise,
    )

    data object Settings: BottomNavDestination(
        route = SettingsRootDestination,
        startDestination = com.waddleup.navigation.settings.SettingsDestination,
        labelResId = R.string.text_settings,
        iconResId = R.drawable.ic_bottom_nav_settings,
    )

    companion object {
        val items = listOf(Home, Statistics, Create, AiSurprise, Settings)

        fun containsDestination(destination: NavDestination?): Boolean {
            return destination?.let { dest ->
                items.any { bottomNavItem ->
                    dest.hierarchy.any { navDest ->
                        navDest.route == bottomNavItem.startDestination::class.qualifiedName
                    }
                }
            } ?: false
        }
    }
}