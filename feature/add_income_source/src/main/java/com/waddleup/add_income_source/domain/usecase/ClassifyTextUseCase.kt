package com.waddleup.add_income_source.domain.usecase

import com.waddleup.add_income_source.domain.MediaPipeSentenceEmbedder
import com.waddleup.core.base.usecase.BaseUseCase
import com.waddleup.core.base.usecase.Result
import kotlin.math.sqrt

/**
 * Created on 6/28/2025
 * @author Kanan Bashir
 */

class ClassifyTextUseCase(
    private val embedder: MediaPipeSentenceEmbedder
): BaseUseCase<ClassifyTextUseCase.Params, String?>() {

    data class Params(
        val cached: List<Pair<String, FloatArray>>,
        val threshold: Float = 0.9f,
        val text: String
    )

    override suspend fun execute(parameters: Params): Result<String?> {
        return try {
            if (parameters.text.isBlank()) return Result.Success(null)

            val inputVec = embedder.embed(parameters.text)

            var bestCategory: String? = null
            var bestScore = Float.NEGATIVE_INFINITY
            for ((label, catVec) in parameters.cached) {
                val score = cosine(inputVec, catVec)
                if (score > bestScore) {
                    bestScore = score
                    bestCategory = label
                }
            }

            if (bestScore >= parameters.threshold) Result.Success(bestCategory) else Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun cosine(a: FloatArray, b: FloatArray): Float {
        var dot = 0f; var normA = 0f; var normB = 0f
        for (i in a.indices) {
            dot   += a[i] * b[i]
            normA += a[i] * a[i]
            normB += b[i] * b[i]
        }
        return dot / (sqrt(normA) * sqrt(normB))
    }
}