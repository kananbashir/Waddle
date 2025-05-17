package com.waddleup.waddle.di

import com.waddleup.core.di.dataModule
import com.waddleup.core.di.networkModule
import com.waddleup.core.di.util.ModuleLoader
import org.koin.core.module.Module
import java.util.ServiceLoader

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
    FeatureModules(
        *ServiceLoader.load(ModuleLoader::class.java)
        .flatMap { it.getModules() }.toTypedArray()
    )
}