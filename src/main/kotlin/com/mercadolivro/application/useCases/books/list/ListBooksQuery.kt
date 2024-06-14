package com.mercadolivro.application.useCases.books.list

import org.springframework.data.domain.Pageable

data class ListBooksQuery(
    val pagination: Pageable
)