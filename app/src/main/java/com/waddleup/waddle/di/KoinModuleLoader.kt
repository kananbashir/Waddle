package com.waddleup.waddle.di

import com.waddleup.add_income_source.di.addIncomeSourceModule
import com.waddleup.auth.di.authModule
import com.waddleup.core.di.dataModule
import com.waddleup.core.di.networkModule
import com.waddleup.home.di.homeModule
import com.waddleup.settings.di.settingsModule
import org.koin.core.module.Module

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

object KoinModuleLoader {
    fun loadModules(): List<Module> =
        Modules.entries.flatMap { it.modules.toList() }
}

private enum class Modules(vararg val modules: Module) {
    CoreModules(dataModule, networkModule),
    MainModules(mainModule),
    AuthModules(authModule),
    HomeModules(homeModule),
    SettingsModules(settingsModule),
    AddIncomeSource(addIncomeSourceModule)
}