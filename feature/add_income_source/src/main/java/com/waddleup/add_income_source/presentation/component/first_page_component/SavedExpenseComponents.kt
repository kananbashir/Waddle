package com.waddleup.add_income_source.presentation.component.first_page_component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.waddleup.add_income_source.viewmodel.state.AddIncomeSourceState
import com.waddleup.app.theme.R
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/24/2025
 * @author Kanan Bashir
 */

@Composable
fun ExpenseCategoryItem(
    modifier: Modifier = Modifier,
    savedExpenseSource: AddIncomeSourceState.ExpenseSource,
    onEditClicked: () -> Unit
) {
    val colors = WaddleTheme.colors
    val shapes = WaddleTheme.shapes

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clip(shapes.small)
            .background(colors.containers.primaryBackground)
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        IncomeSourceSavedExpenseCategoryOptionItem(
            incomeSource = savedExpenseSource.incomeSource
        )

        IncomeAmountSavedExpenseCategoryOptionItem(
            incomeAmount = savedExpenseSource.incomeAmount
        )

        CurrencySavedExpenseCategoryOptionItem(
            currency = savedExpenseSource.currency
        )

        EditSavedExpenseCategoryActionItem(
            onEditClicked = onEditClicked
        )
    }
}

@Composable
private fun EditSavedExpenseCategoryActionItem(
    modifier: Modifier = Modifier,
    onEditClicked: () -> Unit
) {
    SavedExpenseCategoryActionItem(
        modifier = modifier,
        optionText = R.string.text_edit,
        actionIcon = R.drawable.ic_edit,
        onActionIconClicked = onEditClicked
    )
}

@Composable
private fun CurrencySavedExpenseCategoryOptionItem(
    modifier: Modifier = Modifier,
    currency: String
) {
    SavedExpenseCategoryOptionItem(
        modifier = modifier,
        optionText = R.string.text_currency,
        text = currency,
        icon = R.drawable.placeholder_profile_image
    )
}

@Composable
private fun IncomeAmountSavedExpenseCategoryOptionItem(
    modifier: Modifier = Modifier,
    incomeAmount: String
) {
    SavedExpenseCategoryOptionItem(
        modifier = modifier,
        optionText = R.string.text_income_amount,
        text = incomeAmount
    )
}

@Composable
private fun IncomeSourceSavedExpenseCategoryOptionItem(
    modifier: Modifier = Modifier,
    incomeSource: String
) {
    SavedExpenseCategoryOptionItem(
        modifier = modifier,
        optionText = R.string.text_income_source,
        text = incomeSource
    )
}

@Composable
private fun SavedExpenseCategoryActionItem(
    modifier: Modifier = Modifier,
    @StringRes optionText: Int,
    @DrawableRes actionIcon: Int,
    onActionIconClicked: () -> Unit
) {
    val colors = WaddleTheme.colors

    SavedExpenseCategoryItemWrapper(
        modifier = modifier,
        startContent = {
            SavedExpenseCategoryActionOptionText(text = stringResource(optionText))
        },
        endContent = {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) { onActionIconClicked() },
                painter = painterResource(actionIcon),
                contentDescription = null,
                tint = colors.containers.primaryActionText
            )
        }
    )
}

@Composable
private fun SavedExpenseCategoryOptionItem(
    modifier: Modifier = Modifier,
    @StringRes optionText: Int,
    text: String,
    @DrawableRes icon: Int
) {
    SavedExpenseCategoryItemWrapper(
        modifier = modifier,
        startContent = {
            SavedExpenseCategoryOptionText(text = stringResource(optionText))
        },
        endContent = {
            Row {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    painter = painterResource(icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                HorizontalSpacer(10.dp)

                SavedExpenseCategoryText(text = text)
            }
        }
    )
}

@Composable
private fun SavedExpenseCategoryOptionItem(
    modifier: Modifier = Modifier,
    @StringRes optionText: Int,
    text: String
) {
    SavedExpenseCategoryItemWrapper(
        modifier = modifier,
        startContent = {
            SavedExpenseCategoryOptionText(text = stringResource(optionText))
        },
        endContent = {
            SavedExpenseCategoryText(text = text)
        }
    )
}

@Composable
private fun SavedExpenseCategoryActionOptionText(
    modifier: Modifier = Modifier,
    text: String
) {
    SavedExpenseCategoryOptionText(
        modifier = modifier,
        text = text,
        color = WaddleTheme.colors.containers.primaryActionText
    )
}

@Composable
private fun SavedExpenseCategoryOptionText(
    modifier: Modifier = Modifier,
    style: TextStyle = WaddleTheme.typography.body2Medium.PlusJakarta,
    color: Color = WaddleTheme.colors.containers.primaryOptionText,
    text: String
) {
    SavedExpenseCategoryText(
        modifier = modifier,
        text = text,
        style = style,
        color = color
    )
}

@Composable
private fun SavedExpenseCategoryText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = WaddleTheme.typography.body2Medium.Poppins,
    color: Color = WaddleTheme.colors.containers.primaryText
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun SavedExpenseCategoryItemWrapper(
    modifier: Modifier = Modifier,
    startContent: @Composable () -> Unit,
    endContent: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        startContent()
        HorizontalSpacer(16.dp)
        endContent()
    }
}