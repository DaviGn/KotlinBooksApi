package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.responses.FieldErrorResponse
import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minimum
import org.springframework.stereotype.Service

@Service
class CreateBookCommandFieldIValidation : IValidationStrategy<CreateBookCommand> {
    private val validator = Validation<CreateBookRequest> {
        CreateBookRequest::name required  {
            maxLength(126)
        }

        CreateBookRequest::price required  {
            minimum(0.01)
        }
    }

    override fun validate(data: CreateBookCommand): List<FieldErrorResponse> {
        val validationResult = validator(data.request)
        return validationResult.errors.map { it -> FieldErrorResponse(it.message, it.dataPath) }
    }
}