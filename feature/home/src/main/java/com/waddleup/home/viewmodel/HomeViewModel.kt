package com.waddleup.home.viewmodel

import androidx.compose.ui.graphics.Color
import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.app.theme.R
import com.waddleup.home.domain.Transaction
import com.waddleup.home.domain.TransactionCategory
import com.waddleup.home.viewmodel.state.HomeIntent
import com.waddleup.home.viewmodel.state.HomeState
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

class HomeViewModel(
    dispatchersProvider: DispatchersProvider
): BaseViewModel<HomeState, HomeIntent>(dispatchersProvider) {
    override val initialState: HomeState
        get() = HomeState()

    init {
        val list = generateTransactionHistoryItems()

        setState { it.copy(transactions = list) }
    }

    private fun generateTransactionHistoryItems(): PersistentList<Transaction> {
        val categories = listOf(
            Pair(
                R.drawable.transaction_placeholder_bolt,
                Pair(
                    "Bolt taxi",
                    TransactionCategory(
                        color = Color(0xFF8DA787),
                        categoryName = "Transport"
                    )
                )
            ),

            Pair(
                R.drawable.transaction_placeholder_adidas,
                Pair(
                    "Adidas",
                    TransactionCategory(
                        color = Color(0xFFEB445A),
                        categoryName = "Shopping"
                    )
                )
            ),

            Pair(
                R.drawable.transaction_placeholder_kfc,
                Pair(
                    "KFC",
                    TransactionCategory(
                        color = Color(0xFFF7CE46),
                        categoryName = "Restaurant"
                    )
                )
            ),

            Pair(
                R.drawable.transaction_placeholder_push30,
                Pair(
                    "Push30",
                    TransactionCategory(
                        color = Color(0xFF5AAEC4),
                        categoryName = "Sport"
                    )
                )
            )
        )

        val list = List(50) {
            val id = it+1
            val categoryRandom = (0..3).random()

            Transaction(
                id = id,
                image = categories[categoryRandom].first,
                title = categories[categoryRandom].second.first,
                category = categories[categoryRandom].second.second,
                issueDate = "14.05.2024",
                issueTime = "09:11",
                amount = (5..123).random().toDouble(),
                currency = "€"
            )
        }

        return list.toPersistentList()
    }
}