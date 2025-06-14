package com.waddleup.home.domain

import androidx.compose.runtime.Immutable

/**
 * Created on 5/28/2025
 * @author Kanan Bashir
 */

@Immutable
data class Transaction(
    val id: Int,
    val image: Int,
    val title: String,
    val category: TransactionCategory,
    val issueDate: String,
    val issueTime: String,
    val amount: Double,
    val currency: String
)
