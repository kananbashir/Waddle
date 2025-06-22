package com.waddleup.core.presentation.components.input.wrapper.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.waddleup.app.theme.R

/**
 * Created on 6/22/2025
 * @author Kanan Bashir
 */

internal fun getUpdatedValue(
    composable: @Composable (() -> Unit)?,
    explicitText: String?
): @Composable (() -> Unit)? {
    return composable ?: explicitText?.let {
        run { @Composable { WaddleTextFieldText(text = it) } }
    }
}

internal fun getUpdatedValue(
    composable: @Composable (() -> Unit)?,
    explicitRes: Int?,
    contentDes: String,
    onClick: (() -> Unit)?
): @Composable (() -> Unit)? {
    return composable ?: explicitRes?.let {
        run {
            @Composable {
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onClick?.invoke()
                        },
                    painter = painterResource(it),
                    contentDescription = contentDes
                )
            }
        }
    }
}

internal fun getTrailingIcon(
    trailingIcon: @Composable (() -> Unit)?,
    @DrawableRes trailingIconRes: Int?,
    isError: Boolean,
    onTrailingIconClicked: (() -> Unit)?
) = trailingIcon ?: when {
    isError || trailingIconRes != null -> {
        @Composable {
            var isClicked by remember { mutableStateOf(false) }

            AnimatedVisibility(
                visible = isError,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                AnimatedContent(
                    targetState = isClicked,
                    label = ""
                ) { _ ->

                    Icon(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isClicked = !isClicked
                                onTrailingIconClicked?.invoke()
                            },
                        painter = painterResource(trailingIconRes ?: R.drawable.ic_error),
                        contentDescription = null
                    )
                }
            }
        }
    }
    else -> null
}