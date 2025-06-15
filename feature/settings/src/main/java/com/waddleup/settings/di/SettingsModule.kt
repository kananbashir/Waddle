package com.waddleup.settings.di

import org.koin.core.module.dsl.viewModel
import com.waddleup.core.di.util.addModules
import com.waddleup.settings.viewmodel.SettingsViewModel

/**
 * Created on 6/14/2025
 * @author Kanan Bashir
 */

val settingsModule = addModules(
    viewModels = {
        viewModel { SettingsViewModel(get()) }
    }
)