package com.waddleup.navigation.auth

import kotlinx.serialization.Serializable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Serializable data object AuthRootDestination
@Serializable data object AuthLoginDestination
@Serializable data object RegisterDestination
@Serializable data object PasswordRecoveryDestination