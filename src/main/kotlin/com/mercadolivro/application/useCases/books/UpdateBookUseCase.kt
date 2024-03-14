package com.mercadolivro.application.useCases.books

import com.mercadolivro.data.repository.BookRepository
import com.mercadolivro.domain.exceptions.NotFoundException
import com.mercadolivro.domain.mappers.toBookModel
import com.mercadolivro.domain.mappers.toResponse
import com.mercadolivro.domain.requests.UpdateBookRequest
import com.mercadolivro.domain.responses.BookResponse
import com.mercadolivro.domain.responses.bases.IResponse
import com.mercadolivro.domain.responses.bases.NotFoundResponse
import com.mercadolivro.domain.responses.bases.OkObjectResponse
import org.springframework.stereotype.Service

data class UpdateBookUseCase(
    val id: Int,
    val request: UpdateBookRequest
)

@Service
class UpdateBookUseCaseHandler(
    private val bookRepository: BookRepository
) {
    fun handle(data: UpdateBookUseCase): IResponse<BookResponse> {
        val originalBook = bookRepository.findById(data.id);

        if (originalBook.isEmpty)
            return NotFoundResponse();

        val updatedBook = data.request.toBookModel(originalBook.get());
        bookRepository.save(updatedBook);
        val response = updatedBook.toResponse();
        return OkObjectResponse(response);
    }
}