package com.waddleup.core.network

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

interface ConnectivityChecker {
    fun isNetworkAvailable(): Boolean
}