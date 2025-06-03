package com.waddleup.navigation.notifications

import kotlinx.serialization.Serializable

/**
 * Created on 6/1/2025
 * @author Kanan Bashir
 */

@Suppress("unused")
sealed class NotificationsDestinations {
    @Serializable data object NotificationsRoot: NotificationsDestinations()
    @Serializable data object Notifications: NotificationsDestinations()
}