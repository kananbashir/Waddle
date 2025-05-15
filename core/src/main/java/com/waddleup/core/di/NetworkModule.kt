package com.waddleup.core.di

import com.waddleup.core.di.util.addModules
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created on 5/15/2025
 * @author Kanan Bashir
 */

val networkModule = addModules(
    other = {
        single {
            OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl("https://google.com") //TODO: Change with valid base url!
                .client(get())
                .build()
        }
    }
)