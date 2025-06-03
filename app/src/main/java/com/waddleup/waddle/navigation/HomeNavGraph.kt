package com.waddleup.waddle.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.waddleup.core.base.viewmodel.state.UiEvent
import com.waddleup.core.presentation.content.BaseScreen
import com.waddleup.home.presentation.content.HomeContent
import com.waddleup.home.viewmodel.HomeViewModel
import com.waddleup.home.viewmodel.state.HomeIntent
import com.waddleup.home.viewmodel.state.HomeState
import com.waddleup.navigation.home.HomeDestinations
import com.waddleup.waddle.navigation.util.stayAnim
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

/**
 * Created on 5/10/2025
 * @author Kanan Bashir
 */

fun NavGraphBuilder.homeNavGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    navigation<HomeDestinations.HomeRoot>(HomeDestinations.Home) {
        composable<HomeDestinations.Home>(
            enterTransition = { fadeIn() },
            exitTransition = { stayAnim },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            BaseScreen<HomeState, HomeIntent, HomeViewModel>(
                onUiEvent = onUiEvent
            ) { viewModel, state ->
                HomeContent(
                    state = state.value,
                    onIntent = viewModel::onIntent,
                    onEvent = viewModel::sendEvent
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