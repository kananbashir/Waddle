package com.waddleup.home.di

import org.koin.core.module.dsl.viewModel
import com.waddleup.core.di.util.addModules
import com.waddleup.home.viewmodel.HomeViewModel

/**
 * Created on 6/1/2025
 * @author Kanan Bashir
 */

val homeModule = addModules(
    viewModels = {
        viewModel { HomeViewModel(get()) }
    }
)