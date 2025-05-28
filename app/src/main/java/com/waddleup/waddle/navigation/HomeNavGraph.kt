package com.waddleup.waddle.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.home.presentation.content.HomeContent
import com.waddleup.home.viewmodel.state.HomeState
import com.waddleup.navigation.home.HomeDestinations
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import timber.log.Timber

/**
 * Created on 5/10/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.homeNavGraph(
    onUiEvent: (UiEvent) -> Unit,
    navController: NavController
) {
    navigation<HomeDestinations.HomeRoot>(HomeDestinations.Home) {
        Timber.tag("nav_args").d("route=${this.route}")
        composable<HomeDestinations.Home>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                HomeContent(
                    state = HomeState(),
                    onIntent = {},
                    onEvent = {}
                )
            }
        }
    }
}

inline fun <reified R : Any>NavController.getArgsFromRoot() = getBackStackEntry<R>().toRoute<R>()

inline fun <reified T> navTypeOf(
    isNullableAllowed: Boolean = false,
    json: Json = Json { ignoreUnknownKeys = true }
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    // Get the serializer for type T
    private val serializer = Json.serializersModule.serializer<T>()

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { json.decodeFromString(serializer, it) }

    override fun parseValue(value: String): T =
        json.decodeFromString(serializer, Uri.decode(value))

    override fun serializeAsValue(value: T): String =
        Uri.encode(json.encodeToString(serializer, value))

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putString(key, json.encodeToString(serializer, value))
}