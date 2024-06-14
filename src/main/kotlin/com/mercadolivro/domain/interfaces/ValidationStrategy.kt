package com.mercadolivro.domain.interfaces

import com.mercadolivro.domain.responses.FieldErrorResponse

abstract class ValidationStrategy<T> {
    abstract fun validate(data: T) : List<FieldErrorResponse>
}