package com.mercadolivro.domain.dtos

import com.mercadolivro.domain.interfaces.IValidationStrategy

data class ValidationResult(
    val success: Boolean,
    val errors: List<Any>?
) {
    val failed = !success
}

fun <T> List<IValidationStrategy<T>>.validate(data: T): ValidationResult {
    val validationsResults = this.map { it -> it.validate(data) }.flatten()
    val success = validationsResults.isEmpty()
    return ValidationResult(success, validationsResults)
}