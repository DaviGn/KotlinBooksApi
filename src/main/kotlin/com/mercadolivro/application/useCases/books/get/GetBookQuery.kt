package com.mercadolivro.application.useCases.books.get

import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.trendyol.kediatr.Query

data class GetBookQuery(
    val id: Int
) : Query<IResponse>