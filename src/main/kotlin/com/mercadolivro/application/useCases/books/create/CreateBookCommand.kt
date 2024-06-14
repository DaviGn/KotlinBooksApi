package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.domain.requests.CreateBookRequest

data class CreateBookCommand(
    val request: CreateBookRequest
)