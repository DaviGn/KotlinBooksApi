package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.exceptions.NotFoundException
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.responses.BookResponse
import org.springframework.stereotype.Service

data class GetBookUseCase(
    val id: Int
)

@Service
class GetBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: GetBookUseCase): BookResponse {
        val book = bookRepository.findById(data.id);

        if (book.isEmpty)
            throw NotFoundException("Book not found!");

        return book.get().toResponse();
    }
}
