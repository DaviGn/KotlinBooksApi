package com.mercadolivro.domain.mappers

import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.entities.BookModel


fun CreateBookRequest.toBookModel(): BookModel {
    return BookModel(
            name = this.name,
            price = this.price,
    )
}

fun UpdateBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
            id = previousValue.id,
            name = this.name ?: previousValue.name,
            price = this.price ?: previousValue.price
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
            id = this.id,
            name = this.name,
            price = this.price
    )
}