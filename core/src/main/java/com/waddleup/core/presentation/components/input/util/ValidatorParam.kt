package com.waddleup.core.presentation.components.input.util

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

sealed interface ValidatorParam {
    data class ValidationRegexParams(
        val regexString: String,
        val errorMessage: String? = null
    ): ValidatorParam {
        var regex = Regex(regexString)
            private set
    }

    data class ValidationConditionParams(
        val condition: () -> Boolean,
        val errorMessage: String? = null
    ): ValidatorParam
}