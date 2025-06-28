package com.waddleup.add_income_source.domain.repository

/**
 * Created on 6/28/2025
 * @author Kanan Bashir
 */

interface AddIncomeSourceRepository {
    suspend fun fetchCategories(): List<String>
}