package com.mercadolivro.domain.interfaces

import com.mercadolivro.domain.responses.FieldErrorResponse

interface IValidationStrategy<T> {
    fun validate(data: T) : List<FieldErrorResponse>
}