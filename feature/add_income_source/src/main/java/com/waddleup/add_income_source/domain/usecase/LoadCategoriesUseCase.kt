package com.waddleup.add_income_source.domain.usecase

import com.waddleup.add_income_source.domain.MediaPipeSentenceEmbedder
import com.waddleup.add_income_source.domain.repository.AddIncomeSourceRepository
import com.waddleup.core.base.usecase.BaseNoParamUseCase
import com.waddleup.core.base.usecase.Result

/**
 * Created on 6/28/2025
 * @author Kanan Bashir
 */

class LoadCategoriesUseCase(
    private val addIncomeSourceRepository: AddIncomeSourceRepository,
    private val embedder: MediaPipeSentenceEmbedder
): BaseNoParamUseCase<List<Pair<String, FloatArray>>?>() {
    override suspend fun execute(): Result<List<Pair<String, FloatArray>>?> {
        return Result.Success(
            addIncomeSourceRepository.fetchCategories().map { it to embedder.embed(it) }
        )
    }
}