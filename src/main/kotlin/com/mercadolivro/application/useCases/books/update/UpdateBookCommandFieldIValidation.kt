package com.mercadolivro.application.useCases.books.update

import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.FieldErrorResponse
import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minimum
import org.springframework.stereotype.Service

@Service
class UpdateBookCommandFieldIValidation : IValidationStrategy<UpdateBookCommand> {
    private val validator = Validation<UpdateBookRequest> {
        UpdateBookRequest::name required  {
            maxLength(126)
        }

        UpdateBookRequest::price required  {
            minimum(0.01)
        }
    }

    override fun validate(data: UpdateBookCommand): List<FieldErrorResponse> {
        val validationResult = validator(data.request)
        return validationResult.errors.map { it -> FieldErrorResponse(it.message, it.dataPath) }
    }
}