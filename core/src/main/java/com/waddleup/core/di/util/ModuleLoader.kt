package com.waddleup.core.di.util

import org.koin.core.module.Module

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

interface ModuleLoader {
    fun getModules(): List<Module>
}