package com.mercadolivro.application.useCases.books

import com.mercadolivro.domain.dtos.ValidationResult
import com.mercadolivro.domain.interfaces.ValidationStrategy
import com.mercadolivro.domain.responses.bases.BadRequestResponse
import com.mercadolivro.domain.responses.bases.IResponse

abstract class BaseUseCase<TRequest>(
    private val validators: List<ValidationStrategy<TRequest>>
) {
    protected fun runValidations(command: TRequest): ValidationResult {
        val validationsResults = validators.map { it.validate(command) }.flatten()
        val success = validationsResults.isEmpty()
        return ValidationResult(success, validationsResults)
    }

    protected fun returnValidationErrors(validationResult: ValidationResult): IResponse {
        return BadRequestResponse(validationResult.errors)
    }

    fun execute(request: TRequest): IResponse {
        val validationResult = this.runValidations(request)

        if (validationResult.failed) {
            return returnValidationErrors(validationResult)
        }

        return handle(request);
    }

    protected abstract fun handle(request: TRequest) : IResponse
}