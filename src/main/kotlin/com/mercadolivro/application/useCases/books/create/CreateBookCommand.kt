package com.mercadolivro.application.useCases.books.create

import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.trendyol.kediatr.CommandWithResult


data class CreateBookCommand(
    val request: CreateBookRequest
) : CommandWithResult<IResponse<BookResponse>>