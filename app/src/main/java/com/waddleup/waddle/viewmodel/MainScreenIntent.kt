package com.waddleup.waddle.viewmodel

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

sealed class MainScreenIntent {
    data object TrySetStartDestination: MainScreenIntent()
    data object CompleteOnboarding: MainScreenIntent()
}