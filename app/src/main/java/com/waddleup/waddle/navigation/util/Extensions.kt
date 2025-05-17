package com.waddleup.waddle.navigation.util

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.ComposeNavigatorDestinationBuilder
import androidx.navigation.get
import kotlin.reflect.KType

/**
 * Created on 4/12/2025
 * @author Kanan Bashir
 */

inline fun <reified T: Any> NavGraphBuilder.waddleNavigation(
    startDestination: Any,
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    builder: NavGraphBuilder.() -> Unit
): Unit = destination(NavGraphBuilder(provider, startDestination, T::class, typeMap).apply(builder))

inline fun <reified T : Any> NavGraphBuilder.waddleComposable(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        { enterNavAnim },
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        { exitNavAnim },
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        { popEnterNavAnim },
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        { popExitNavAnim },
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    destination(
        ComposeNavigatorDestinationBuilder(
            provider[ComposeNavigator::class],
            T::class,
            typeMap,
            content
        )
            .apply {
                deepLinks.forEach { deepLink -> deepLink(deepLink) }
                this.enterTransition = enterTransition
                this.exitTransition = exitTransition
                this.popEnterTransition = popEnterTransition
                this.popExitTransition = popExitTransition
                this.sizeTransform = sizeTransform
            }
    )
}