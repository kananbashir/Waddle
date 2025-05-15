package com.waddleup.core.di.util

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

fun addModules(
    apis: (Module.() -> Unit)? = null,
    repositories: (Module.() -> Unit)? = null,
    viewModels: (Module.() -> Unit)? = null,
    useCases: (Module.() -> Unit)? = null,
    dispatchers: (Module.() -> Unit)? = null,
    dataStorage: (Module.() -> Unit)? = null,
    other: (Module.() -> Unit)? = null,
): Module {
    return module {
        apis?.invoke(this)
        repositories?.invoke(this)
        viewModels?.invoke(this)
        useCases?.invoke(this)
        dispatchers?.invoke(this)
        dataStorage?.invoke(this)
        other?.invoke(this)
    }
}