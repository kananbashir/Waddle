package com.waddleup.core.navigation.destinations.auth

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
@Serializable
sealed class AuthDestinations {
    @Serializable data object AuthRoot: AuthDestinations()
    @Serializable data object Login: AuthDestinations()
    @Serializable data object Register: AuthDestinations()
    @Serializable data object PasswordRecovery: AuthDestinations()
}