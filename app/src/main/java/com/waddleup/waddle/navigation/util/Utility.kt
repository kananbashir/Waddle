package com.waddleup.waddle.navigation.util

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntSize

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

val slideInFromRightAnim = slideInHorizontally(tween(600, 0, FastOutSlowInEasing)) { it * 2 }
val slideInFromTopAnim = slideInVertically(tween(600, 0, FastOutSlowInEasing)) { -(it * 2) }

val slideOutToLeftAnim = slideOutHorizontally(tween(600, 100, FastOutSlowInEasing)) { -(it*0.2).toInt() }
val slideOutToBottomAnim = slideOutVertically(tween(600, 100, FastOutSlowInEasing)) { (it*0.2).toInt() }
val stayAnim = shrinkOut { IntSize(it.width, it.height) }

val popEnterFromLeftAnim = slideInHorizontally(tween(200, 0, FastOutSlowInEasing)) { -(it*0.2).toInt() }
val popEnterFromBottomAnim = slideInVertically(tween(200, 0, FastOutSlowInEasing)) { (it*0.2).toInt() }

val popExitToRightAnim = slideOutHorizontally(tween(600, 0, FastOutSlowInEasing)) { it * 2 }
val popExitToTopAnim = slideOutVertically(tween(600, 0, FastOutSlowInEasing)) { -(it * 2) }