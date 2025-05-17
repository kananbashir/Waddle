package com.waddleup.waddle.viewmodel

import androidx.compose.runtime.Immutable

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

@Immutable
data class MainScreenState(
    val isLoading: Boolean = true,
    val startDestination: Any? = null
)