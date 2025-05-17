package com.waddleup.auth.di

import com.waddleup.auth.data.remote.AuthService
import com.waddleup.auth.data.repository.AuthRepositoryImpl
import com.waddleup.auth.domain.repository.AuthRepository
import com.waddleup.auth.login.domain.usecase.LoginUseCase
import com.waddleup.auth.password_recovery.domain.usecase.ConfirmOtpUseCase
import com.waddleup.auth.password_recovery.domain.usecase.SendOtpToEmailUseCase
import com.waddleup.auth.password_recovery.domain.usecase.UpdateAccountPasswordUseCase
import com.waddleup.auth.viewmodel.AuthViewModel
import com.waddleup.core.di.util.addModules
import org.koin.core.module.dsl.viewModel
import retrofit2.Retrofit

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

val authModule = addModules(
    apis = {
        single { get<Retrofit>().create(AuthService::class.java) }
    },
    repositories = {
        single<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get(), get()) }
    },
    viewModels = {
        viewModel { AuthViewModel(get(), get(), get(), get(), get()) }
    },
    useCases = {
        factory { LoginUseCase(get()) }
        factory { SendOtpToEmailUseCase(get()) }
        factory { ConfirmOtpUseCase(get()) }
        factory { UpdateAccountPasswordUseCase(get()) }
    }
)