package com.waddleup.home.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created on 6/1/2025
 * @author Kanan Bashir
 */

class HomeScreenNestedScrollConnection(
    private val progressAnim: Animatable<Float, AnimationVector1D>,
    private val lazyListState: LazyListState,
    private val dragRange: Float,
    private val snapThreshold: Float,
    private val scope: CoroutineScope
): NestedScrollConnection {
    private val isListAtTop: Boolean
        get() = lazyListState.firstVisibleItemIndex == 0 &&
                lazyListState.firstVisibleItemScrollOffset == 0

    private fun calculateNewProgress(deltaY: Float): Float =
        (progressAnim.value - deltaY / dragRange).coerceIn(0f, 1f)

    private fun consumeScroll(deltaY: Float): Offset {
        val newProgress = calculateNewProgress(deltaY)
        val consumedY = (progressAnim.value - newProgress) * dragRange

        if (consumedY != 0f) {
            scope.launch { progressAnim.snapTo(newProgress) }
            return Offset(0f, consumedY)
        }
        return Offset.Zero
    }

    private suspend fun animateToTarget(target: Float) {
        progressAnim.animateTo(
            targetValue = target,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    }

    private fun getSnapTarget(): Float =
        if (progressAnim.value > snapThreshold) 1f else 0f

    private val isProgressInTransition: Boolean
        get() = progressAnim.value in 0f..1f &&
                progressAnim.value !in listOf(0f, 1f)

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val dy = available.y
        return if (dy < 0f) consumeScroll(dy) else Offset.Zero
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val dyRemaining = available.y
        return if (dyRemaining > 0f && isListAtTop) consumeScroll(dyRemaining) else Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        if (!isProgressInTransition) return Velocity.Zero
        val target = getSnapTarget()
        if (target == 0f && !isListAtTop) return Velocity.Zero
        animateToTarget(target)
        return available
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        return if (available.y > 0f && isListAtTop) {
            animateToTarget(getSnapTarget())
            available
        } else Velocity.Zero
    }
}