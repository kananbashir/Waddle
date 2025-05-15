package com.waddleup.core.presentation.util

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

@Suppress("KotlinConstantConditions")
internal fun checkValues(
    value1: Any?,
    value2: Any?,
    onBothNull: (() -> Unit)? = null,
    onBothNotNull: (() -> Unit)? = null
): Boolean {
    val isValue1Null = value1 == null
    val isValue2Null = value2 == null

    when {
        isValue1Null && isValue2Null -> {
            onBothNull?.invoke() ?: throw IllegalStateException(
                "Both value1 and value2 are null. One of them must be assigned" +
                        "Current state: value1=$value1, value2=$value2"
            )
        }
        !isValue1Null && !isValue2Null -> {
            onBothNotNull?.invoke() ?: throw IllegalStateException(
                "Both value1 and value2 are not null. One of them must be null" +
                        "Current state: value1=$value1, value2=$value2"
            )
        }
    }

    return true
}