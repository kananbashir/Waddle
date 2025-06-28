package com.waddleup.add_income_source.di

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.waddleup.add_income_source.data.repository.AddIncomeSourceRepositoryImpl
import com.waddleup.add_income_source.domain.MediaPipeSentenceEmbedder
import com.waddleup.add_income_source.domain.repository.AddIncomeSourceRepository
import com.waddleup.add_income_source.domain.usecase.ClassifyTextUseCase
import com.waddleup.add_income_source.domain.usecase.LoadCategoriesUseCase
import com.waddleup.add_income_source.viewmodel.AddIncomeSourceViewModel
import com.waddleup.core.di.util.addModules
import org.koin.core.module.dsl.viewModel

/**
 * Created on 6/23/2025
 * @author Kanan Bashir
 */

val addIncomeSourceModule = addModules(
    viewModels = {
        viewModel { AddIncomeSourceViewModel(get(), get(), get()) }
    },
    useCases = {
        factory { ClassifyTextUseCase(get()) }
        factory { LoadCategoriesUseCase(get(), get()) }
    },
    repositories = {
        single<AddIncomeSourceRepository> { AddIncomeSourceRepositoryImpl(get(), get(),get(),get()) }
    },
    other = {
        single { MediaPipeSentenceEmbedder(get()) }
        single<FirebaseRemoteConfig> { FirebaseRemoteConfig.getInstance() }
    }
)