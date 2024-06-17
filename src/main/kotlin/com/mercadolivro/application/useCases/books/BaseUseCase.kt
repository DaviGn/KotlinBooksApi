package com.mercadolivro.application.useCases.books

import com.mercadolivro.domain.dtos.ValidationResult
import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.responses.bases.*

abstract class BaseUseCaseWithoutResponse<TRequest>(
    validators: List<IValidationStrategy<TRequest>>
) : BaseUseCase<TRequest, Unit>(validators)

abstract class BaseUseCase<TRequest, TResponse>(
    private val validators: List<IValidationStrategy<TRequest>>
) {
    fun execute(request: TRequest): IResponse {
        val validationResult = this.runValidations(request)

        if (validationResult.failed) {
            return processValidationErrors(validationResult)
        }

        return handle(request);
    }

    protected abstract fun handle(request: TRequest): IResponse

    private fun runValidations(request: TRequest): ValidationResult {
        for (validator in validators) {
            val validationsResult = validator.validate(request)
            val success = validationsResult.isEmpty()

            if (!success)
                return ValidationResult(false, validationsResult.map { it.getResponse() })
        }

        return ValidationResult(true, null)
    }

    private fun processValidationErrors(validationResult: ValidationResult): IResponse {
        return BadRequestResponse(validationResult.errors)
    }

    protected fun ok(): IResponse {
        return OkResponse()
    }

    protected fun ok(response: TResponse): IResponse {
        return OkObjectResponse(response)
    }

    protected fun created(response: TResponse): IResponse {
        return CreatedResponse(response)
    }

    protected fun noContent(): IResponse {
        return NoContentResponse()
    }

    protected fun notFound(): IResponse {
        return NotFoundResponse()
    }

    protected fun badRequest(response: Any): IResponse {
        return badRequest(response)
    }
}