package com.mercadolivro.application.useCases.books.update

import com.mercadolivro.domain.requests.UpdateBookRequest

data class UpdateBookCommand(
    val id: Int,
    val request: UpdateBookRequest
)