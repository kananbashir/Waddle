package com.waddleup.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.waddleup.core.presentation.components.other.HorizontalSpacer
import com.waddleup.core.presentation.components.other.VerticalSpacer
import com.waddleup.home.domain.Transaction
import com.waddleup.theme.WaddleTheme

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    transaction.apply {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TransactionItemStart(
                transaction = transaction
            )

            TransactionItemEnd(
                transaction = transaction
            )
        }
    }
}

@Composable
private fun TransactionItemStart(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    transaction.apply {
        Row(
            modifier = modifier
        ) {
            TransactionItemStartImage(
                transaction = transaction
            )

            HorizontalSpacer(12.dp)

            TransactionItemStartDetails(
                transaction = transaction
            )
        }
    }
}

@Composable
private fun TransactionItemEnd(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    transaction.apply {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${amount}${currency}",
                style = WaddleTheme.typography.body2Medium.Poppins,
                color = WaddleTheme.colors.primaryText
            )

            Text(
                text = "$issueDate • $issueTime",
                style = WaddleTheme.typography.overlineMedium.Poppins,
                color = WaddleTheme.colors.quaternaryText
            )
        }
    }
}

@Composable
private fun TransactionItemStartImage(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    transaction.apply {
        Image(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = image),
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun TransactionItemStartDetails(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    transaction.apply {
        Column(
            modifier = modifier
        ) {
            Text(
                text = title,
                style = WaddleTheme.typography.body2Regular.Poppins,
                color = WaddleTheme.colors.primaryText
            )

            VerticalSpacer(4.dp)

            TransactionItemStartCategoryPart(
                transaction = transaction
            )
        }
    }
}

@Composable
private fun TransactionItemStartCategoryPart(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    transaction.apply {
        Row(
            modifier = modifier
                .background(
                    color = WaddleTheme.colors.inputFieldBackground,
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        color = category.color,
                        shape = CircleShape
                    )
            )

            Text(
                text = category.categoryName,
                style = WaddleTheme.typography.overline2Regular.Poppins,
                color = WaddleTheme.colors.primaryText
            )
        }
    }
}