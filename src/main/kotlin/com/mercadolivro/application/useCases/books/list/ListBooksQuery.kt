package com.mercadolivro.application.useCases.books.list

import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.trendyol.kediatr.Query
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

data class ListBooksQuery(
    val pagination: Pageable
) : Query<IResponse<Page<BookResponse>>>