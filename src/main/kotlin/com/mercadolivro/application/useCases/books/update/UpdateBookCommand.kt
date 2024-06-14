package com.mercadolivro.application.useCases.books.update

import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.bases.IResponse
import com.trendyol.kediatr.CommandWithResult

data class UpdateBookCommand(
    val id: Int,
    val request: UpdateBookRequest
) : CommandWithResult<IResponse>