package com.mercadolivro.domain.utils

import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.responses.FieldErrorResponse

data class ValidationResult(
    val success: Boolean,
    val errors: List<FieldErrorResponse>?
) {
    val failed = !success
}

fun <T> List<ValidationStrategy<T>>.validate(data: T): ValidationResult {
    val validationsResults = this.map { it -> it.validate(data) }.flatten()
    val success = validationsResults.isEmpty()
    return ValidationResult(success, validationsResults)
}