package com.waddleup.home.viewmodel.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.waddleup.home.R
import com.waddleup.home.domain.Transaction
import com.waddleup.home.domain.TransactionCategory

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

@Immutable
data class HomeState(
    val dailyLimit: Double? = null,
    val totalSaveOfMonth: Double? = null,
    val totalSpendingOfTheMonth: Double? = null,
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = listOf(
        Transaction(
            id = 1,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 2,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 3,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 4,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        ),
        Transaction(
            id = 5,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 6,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 7,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 8,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        ),
        Transaction(
            id = 9,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 10,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 11,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 12,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        ),
        Transaction(
            id = 13,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 14,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 15,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 16,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        ),
        Transaction(
            id = 17,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 18,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 19,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 20,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        ),
        Transaction(
            id = 21,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 22,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 23,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 24,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        ),
        Transaction(
            id = 25,
            image = R.drawable.transaction_placeholder_bolt,
            title = "Bolt taxi",
            category = TransactionCategory(
                color = Color(0xFF8DA787),
                categoryName = "Transport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 30.50,
            currency = "€"
        ),
        Transaction(
            id = 26,
            image = R.drawable.transaction_placeholder_adidas,
            title = "Adidas",
            category = TransactionCategory(
                color = Color(0xFFEB445A),
                categoryName = "Shopping"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 350.0,
            currency = "€"
        ),
        Transaction(
            id = 27,
            image = R.drawable.transaction_placeholder_kfc,
            title = "KFC",
            category = TransactionCategory(
                color = Color(0xFFF7CE46),
                categoryName = "Restaurant"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 25.0,
            currency = "€"
        ),
        Transaction(
            id = 28,
            image = R.drawable.transaction_placeholder_push30,
            title = "Push30",
            category = TransactionCategory(
                color = Color(0xFF5AAEC4),
                categoryName = "Sport"
            ),
            issueDate = "14.05.2024",
            issueTime = "09:11",
            amount = 150.0,
            currency = "€"
        )
    )
)
