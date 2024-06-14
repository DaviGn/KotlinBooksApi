package com.mercadolivro.application.useCases.books.delete

import com.mercadolivro.domain.responses.bases.IResponse
import com.trendyol.kediatr.CommandWithResult

data class DeleteBookCommand(
    val id: Int
) : CommandWithResult<IResponse<Unit>>