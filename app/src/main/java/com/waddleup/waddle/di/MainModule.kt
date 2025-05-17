package com.waddleup.waddle.di

import com.waddleup.app.domain.CheckOnboardingStatusUseCase
import com.waddleup.app.domain.CompleteOnboardingUseCase
import com.waddleup.auth.domain.usecase.CheckLoggedInStatusUseCase
import com.waddleup.core.di.util.addModules
import com.waddleup.onboarding.data.repository.OnboardingRepository
import com.waddleup.onboarding.domain.repository.OnboardingRepositoryImpl
import com.waddleup.waddle.viewmodel.MainViewModel

/**
 * Created on 5/16/2025
 * @author Kanan Bashir
 */

val mainModule = addModules(
    viewModels = {
        single<MainViewModel> { MainViewModel(get(), get(), get(), get()) }
    },

    useCases = {
        factory<CheckOnboardingStatusUseCase> { CheckOnboardingStatusUseCase(get()) }
        factory<CompleteOnboardingUseCase> { CompleteOnboardingUseCase(get()) }
        factory<CheckLoggedInStatusUseCase> { CheckLoggedInStatusUseCase(get()) }
    },

    dataStorage = {
        single<OnboardingRepository> { OnboardingRepositoryImpl(get()) }
    }
)