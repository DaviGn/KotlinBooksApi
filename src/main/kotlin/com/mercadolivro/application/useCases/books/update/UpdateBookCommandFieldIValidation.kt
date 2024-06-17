package com.mercadolivro.application.useCases.books.update

import com.mercadolivro.domain.interfaces.IValidationStrategy
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.errors.FieldError
import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minimum
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service

@Service
@Order(1) // define DI order
class UpdateBookCommandFieldValidation : IValidationStrategy<UpdateBookCommand> {
    private val validator = Validation<UpdateBookRequest> {
        UpdateBookRequest::name required  {
            maxLength(126)
        }

        UpdateBookRequest::price required  {
            minimum(0.01)
        }
    }

    override fun validate(data: UpdateBookCommand): List<FieldError> {
        val validationResult = validator(data.request)
        return validationResult.errors.map { it -> FieldError(it.message, it.dataPath) }
    }
}