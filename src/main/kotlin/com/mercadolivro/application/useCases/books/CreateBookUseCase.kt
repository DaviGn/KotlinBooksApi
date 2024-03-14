package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.requests.CreateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.CreatedResponse
import com.mercadolivro.domain.responses.bases.IResponse
import org.springframework.stereotype.Service

data class CreateBookUseCase(
    val request: CreateBookRequest
)

@Service
class CreateBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: CreateBookUseCase): IResponse<BookResponse> {
        val book = data.request.toBookModel();
        bookRepository.save(book);
        val response = book.toResponse();
        return CreatedResponse(response);
    }
}