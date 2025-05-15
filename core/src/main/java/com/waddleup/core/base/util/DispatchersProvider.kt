package com.waddleup.core.base.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created on 5/14/2025
 * @author Kanan Bashir
 */

interface DispatchersProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}