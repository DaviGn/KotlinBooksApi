package com.mercadolivro.domain.interfaces

interface IValidationStrategy<T> {
    fun validate(data: T) : List<IErrorResponse>
}