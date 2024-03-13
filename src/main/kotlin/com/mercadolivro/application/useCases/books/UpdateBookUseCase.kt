package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.exceptions.NotFoundException
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import org.springframework.stereotype.Service

data class UpdateBookUseCase(
    val id: Int,
    val request: UpdateBookRequest
)

@Service
class UpdateBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: UpdateBookUseCase): BookResponse {
        val originalBook = bookRepository.findById(data.id);

        if (originalBook.isEmpty)
            throw NotFoundException("Book not found!");

        val updatedBook = data.request.toBookModel(originalBook.get());
        bookRepository.save(updatedBook);
        return updatedBook.toResponse();
    }
}