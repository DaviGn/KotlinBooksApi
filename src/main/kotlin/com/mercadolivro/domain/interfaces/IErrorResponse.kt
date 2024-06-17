package com.mercadolivro.domain.interfaces

interface IErrorResponse<T> {
    fun getResponse() : T
}