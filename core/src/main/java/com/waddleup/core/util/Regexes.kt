package com.waddleup.core.util

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

object Regexes {
    const val VALID_EMAIL: String = "^(?:[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.[a-zA-Z]{2,})?\$"
    const val NO_WHITE_SPACE: String = "^[^\\s]*\$"
    const val NOT_EMPTY: String = "^[^\\s]+\$"
    const val MAIN_PASSWORD: String = "^(?=.*[a-zA-Z])(?=.*\\d).+\$" //At least one letter and one number.

    fun minChars(countInclusive: Int) = "^(?:\\s*\\S){$countInclusive,}\\s*\$"
}