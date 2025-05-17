package com.waddleup.waddle.navigation.util

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

val enterNavAnim = slideInHorizontally(tween(600, 0, FastOutSlowInEasing)) { it * 2 }
val exitNavAnim = slideOutHorizontally(tween(600, 100, FastOutSlowInEasing)) { -(it*0.2).toInt() }
val popEnterNavAnim = slideInHorizontally(tween(200, 0, FastOutSlowInEasing)) { -(it*0.2).toInt() }
val popExitNavAnim = slideOutHorizontally(tween(600, 0, FastOutSlowInEasing)) { it * 2 }