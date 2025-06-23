package com.waddleup.add_income_source.di

import com.waddleup.add_income_source.viewmodel.AddIncomeSourceViewModel
import com.waddleup.core.di.util.addModules
import org.koin.core.module.dsl.viewModel

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

val addIncomeSourceModule = addModules(
    viewModels = {
        viewModel { AddIncomeSourceViewModel(get()) }
    }
)