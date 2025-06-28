package com.waddleup.add_income_source.domain

import android.content.Context
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.text.textembedder.TextEmbedder

/**
 * Created on 6/28/2025
 * @author Kanan Bashir
 */

class MediaPipeSentenceEmbedder(
    context: Context
) {
    private val embedder: TextEmbedder = TextEmbedder.createFromOptions(
        context,
        TextEmbedder.TextEmbedderOptions.builder()
            .setBaseOptions(
                BaseOptions.builder().setModelAssetPath("use_embedder.tflite")
                    .build()
            ).build()
    )

    fun embed(text: String): FloatArray = embedder.embed(text)
        .embeddingResult().embeddings().first().floatEmbedding()
}